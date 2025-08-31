package uk.gov.gchq.magmacore.examples.plans;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.model.ClassOfState;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Plan;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Requirement;
import uk.gov.gchq.magmacore.hqdm.model.RequirementSpecification;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.ClassOfStateBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.PlanBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.PointInTimeBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.RequirementBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.RequirementSpecificationBuilder;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * An example of plans using Possible Worlds.
 */
public class PossibleWorldsExampleTest {

    private static final String PREFIXES = """
        @prefix tst: <https://example.com/test#> .
        @prefix date: <https://example.com/date#> .
        @prefix h: <https://hqdmtop.github.io/hqdmOntology/1.0.0#> .
        @prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
        @prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
        @prefix skos: <http://www.w3.org/2004/02/skos/core#> .
                """;

    private static final IriBase BASE = new IriBase("tst", "https://example.com/test#");
    private static final IriBase DATE_BASE = new IriBase("date", "https://example.com/date#");
    private static final Instant START = Instant.now();
    private static long nextIri = 0L;

    private static String nextIri() {
        return Long.toString(nextIri++);
    }

    @Test
    public void testPlans() {
        final var objects = new HashMap<IRI, Thing>();

        final var possibleWorld = getTheRealWorld();
        objects.put(possibleWorld.getId(), possibleWorld);

        final var startEvent = getPlanStartEvent(possibleWorld);
        objects.put(startEvent.getId(), startEvent);

        final var endEvent = getPlanEndEvent(possibleWorld);
        objects.put(endEvent.getId(), endEvent);

        final var plan = getPlan(possibleWorld, startEvent, endEvent);
        objects.put(plan.getId(), plan);

        final var servicedCarClassOfState = getServicedCarClassOfState();
        Arrays.stream(servicedCarClassOfState).forEach(cos -> {
            objects.put(cos.getId(), cos);
        });

        final var carRequirementSpecification = getServicedCarRequirementSpecification(servicedCarClassOfState);
        objects.put(carRequirementSpecification.getId(), carRequirementSpecification);

        final var carRequirementStartEvent = getCarRequirementStartEvent(possibleWorld);
        objects.put(carRequirementStartEvent.getId(), carRequirementStartEvent);

        final var carRequirementEndEvent = getCarRequirementEndEvent(possibleWorld);
        objects.put(carRequirementEndEvent.getId(), carRequirementEndEvent);

        final var carRequirement = getCarRequirement(possibleWorld, plan, carRequirementSpecification, carRequirementStartEvent, carRequirementEndEvent);
        objects.put(carRequirement.getId(), carRequirement);

        final var mcs = MagmaCoreServiceFactory.createWithJenaDatabase();
        mcs.runInWriteTransaction(svc -> {
            objects.values().stream().forEach(svc::create);
            return svc;
        });

        System.out.println();
        System.out.println(PREFIXES);
        mcs.exportTtl(System.out);
        System.out.println();
    }

    private Event getCarRequirementEndEvent(final PossibleWorld possibleWorld) {
        final var date = START.plus(365, ChronoUnit.DAYS);
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(possibleWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private Event getCarRequirementStartEvent(final PossibleWorld possibleWorld) {
        final var date = START.plus(300, ChronoUnit.DAYS);
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(possibleWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private ClassOfState[] getServicedCarClassOfState() {
        final var carStateIri = new IRI(BASE, nextIri());
        final var serviceStateIri = new IRI(BASE, nextIri());
        final var workingStateIri = new IRI(BASE, nextIri());
        final var carState = new ClassOfStateBuilder(carStateIri).build();
        final var serviceState = new ClassOfStateBuilder(serviceStateIri).build();
        final var workingState = new ClassOfStateBuilder(workingStateIri).build();
        carState.addStringValue(HQDM.SKOS_DEFINITION, "Car State.");
        serviceState.addStringValue(HQDM.SKOS_DEFINITION, "Car without 50k mile service.");
        workingState.addStringValue(HQDM.SKOS_DEFINITION, "Functioning");
        return new ClassOfState[] {
            carState,
            serviceState,
            workingState
        };
    }

    private Requirement getCarRequirement(
        final PossibleWorld possibleWorld,
        final Plan plan,
        final RequirementSpecification carRequirementSpecification,
        final Event beginning,
        final Event ending) {

        final var iri = new IRI(BASE, nextIri());
        final var req = new RequirementBuilder(iri)
            .part_Of_Plan_M(plan)
            .part_Of_Possible_World_M(possibleWorld)
            .defined_By_M(carRequirementSpecification)
            .beginning(beginning)
            .ending(ending)
            .build();
        req.addStringValue(HQDM.SKOS_DEFINITION, "Car with 50k mile service requirement.");

        return req;
    }

    private RequirementSpecification getServicedCarRequirementSpecification(final ClassOfState... classOfState) {
        if (classOfState.length == 0) {
            throw new InvalidParameterException("Need at  least one classOfState");
        }
        final var iri = new IRI(BASE, nextIri());
        final var spec = new RequirementSpecificationBuilder(iri)
            .intersection_Of_M(classOfState[0])
            .build();
        spec.addStringValue(HQDM.SKOS_DEFINITION, "Car with 50k mile service requirement Spec.");
        spec.addValue(RDFS.RDFS_SUB_CLASS_OF, classOfState[0].getId());
        if (classOfState.length > 1) {
            for (int i = 1; i < classOfState.length; i++) {
                spec.addValue(HQDM.INTERSECTION_OF, classOfState[i].getId());
                spec.addValue(RDFS.RDFS_SUB_CLASS_OF, classOfState[i].getId());
            }
        }
        return spec;
    }

    private Plan getPlan(final PossibleWorld possibleWorld, final Event startEvent, final Event endEvent) {

        final var planIri = new IRI(BASE, nextIri());
        final var plan = new PlanBuilder(planIri)
            .part_Of_Possible_World_M(possibleWorld)
            .beginning(startEvent)
            .ending(endEvent)
            .build();

        plan.addStringValue(HQDM.SKOS_DEFINITION, "Car Servicing Plan");
        return plan;
    }

    private Event getPlanEndEvent(final PossibleWorld possibleWorld) {
        final var date = START.plus(365, ChronoUnit.DAYS);
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(possibleWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private Event getPlanStartEvent(final PossibleWorld possibleWorld) {
        final var date = START;
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(possibleWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private PossibleWorld getTheRealWorld() {
        final var possibleWorldIri = new IRI(BASE, nextIri());
        final var possibleWorld = SpatioTemporalExtentServices.createPossibleWorld(possibleWorldIri);
        possibleWorld.addStringValue(HQDM.SKOS_DEFINITION, "The Real World");
        return possibleWorld;
    }

    private static String instantToString(final Instant when) {
        return Long.toString(when.getEpochSecond()) + Long.toString(when.getNano());
    }
}
