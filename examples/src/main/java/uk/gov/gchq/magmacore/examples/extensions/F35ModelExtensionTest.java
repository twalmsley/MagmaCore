package uk.gov.gchq.magmacore.examples.extensions;

import java.util.UUID;

import uk.gov.gchq.magmacore.examples.extensions.f35.ext.F35ExtensionService;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135Engine;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135EngineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135EngineSystem;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135Turbine;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135TurbineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135VerticalLiftSystem;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135VerticalLiftSystemComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35Aircraft;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35Airframe;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35AirframeComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EjectionSeatComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EngineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35TurbineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35VerticalLiftSystemComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.US16EEjectionSeat;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Test the model extension provided by the model-extension-f35 module.
 *
 * <p>
 * Run this using mvn exec:java
 * -Dexec.mainClass=uk.gov.gchq.magmacore.examples.extensions.F35ModelExtensionTest
 * </p>
 */
public class F35ModelExtensionTest {

    // Declare an IRI base for the data to be created.
    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    private static final F35ExtensionService f35 = new F35ExtensionService();

    /**
     * Main entry point.
     *
     * @param args
     *            a String array
     */
    public static void main(final String[] args) {
        // Create a MagmaCoreService with an in-memory Apache Jena database.
        final var mcs = MagmaCoreServiceFactory.createWithJenaDatabase();

        // The entity will be a part of a dummy possible_world, we just use the IRI
        // rather than creating the possible_world for this example.
        final var possibleWorldIri = iri();

        final var aircraftIri = new IRI(TEST_BASE, UUID.randomUUID().toString());
        final F35Aircraft aircraft = f35.createEntity(Constants.F35_AIRCRAFT_TYPE_NAME, aircraftIri);
        aircraft.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);

        // Persist the entity in the database.
        mcs.runInWriteTransaction(svc -> {
            svc.create(aircraft);
            return svc;
        });

        // Read the entity back and assert that it matches the original.
        mcs.runInReadTransaction(svc -> {
            // TODO: Implement this.
            return svc;
        });
        
        run();
    }

    /**
     * Build an F35.
     */
    private static void run() {
        // ------------------------------------------------------------------------------------------------
        // Create the OrdinaryFunctionalObjects that will be installed in an aircraft
        // ------------------------------------------------------------------------------------------------

        // Manufacture a VLS
        final var vlsIri = iri();
        final F135VerticalLiftSystem vls = f35.createEntity(Constants.F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME, vlsIri);

        // Manufacture a Turbine
        final var turbineIri = iri();
        final F135Turbine turbine = f35.createEntity(Constants.F135_TURBINE_TYPE_NAME, turbineIri);

        // Manufacture an Engine
        final var engineIri = iri();
        final F135Engine engine = f35.createEntity(Constants.F135_ENGINE_TYPE_NAME, engineIri);

        // Manufacture an Ejection Seat
        final var ejectionSeatIri = iri();
        final US16EEjectionSeat ejectionSeat = f35.createEntity(Constants.US16E_EJECTION_SEAT_TYPE_NAME, ejectionSeatIri);

        // Manufacture an Airframe
        final var airframIri = iri();
        final F35Airframe airframe = f35.createEntity(Constants.F35_AIRFRAME_TYPE_NAME, airframIri);

        // ------------------------------------------------------------------------------------------------
        // Create the engine Functional System.
        // ------------------------------------------------------------------------------------------------

        // Manufacture an Engine System
        final var engineSystem1Iri = iri();
        final var engineComponentIri = iri();
        final var turbineComponentIri = iri();
        final var vlsComponentIri = iri();

        final F135EngineComponent engineComponent = f35.createEntity(Constants.F135_ENGINE_COMPONENT_TYPE_NAME, engineComponentIri);
        final F135TurbineComponent turbineComponent = f35.createEntity(Constants.F135_TURBINE_COMPONENT_TYPE_NAME, turbineComponentIri);
        final F135VerticalLiftSystemComponent vlsComponent = f35.createEntity(Constants.F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME, vlsComponentIri);

        final F135EngineSystem engineSystem1 = f35.createEntity(Constants.F135_ENGINE_SYSTEM_TYPE_NAME, engineSystem1Iri);
        engineSystem1.setEngine(engineComponent);
        engineSystem1.setTurbine(turbineComponent);
        engineSystem1.setVerticalLiftSystem(vlsComponent);

        // ------------------------------------------------------------------------------------------------
        // Create the Aircraft Functional System
        // ------------------------------------------------------------------------------------------------

        // Manufacture an Aircraft
        final var f35AircraftSystemIri = iri();
        final var f35AirframeComponentIri = iri();
        final var f35EngineComponentIri = iri();
        final var f35EjectionSeatComponentIri = iri();
        final var f35VerticalLiftSystemComponentIri = iri();
        final var f35TurbineComponentIri = iri();

        final F35Aircraft aircraft2101 = f35.createEntity(Constants.F35_AIRCRAFT_TYPE_NAME, f35AircraftSystemIri);
        final F35AirframeComponent f35AirframeComponent = f35.createEntity(Constants.F35_AIRFRAME_COMPONENT_TYPE_NAME, f35AirframeComponentIri);
        final F35EngineComponent f35EngineComponent = f35.createEntity(Constants.F35_ENGINE_COMPONENT_TYPE_NAME, f35EngineComponentIri);
        final F35EjectionSeatComponent f35EjectionSeatComponent = f35.createEntity(Constants.F35_EJECTION_SEAT_COMPONENT_TYPE_NAME,
            f35EjectionSeatComponentIri);
        final F35VerticalLiftSystemComponent f35VerticalLiftSystemComponent = f35.createEntity(Constants.F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME,
            f35VerticalLiftSystemComponentIri);
        final F35TurbineComponent f35TurbineComponent = f35.createEntity(Constants.F35_TURBINE_COMPONENT_TYPE_NAME, f35TurbineComponentIri);

        aircraft2101.setEngine(f35EngineComponent);
        aircraft2101.setTurbine(f35TurbineComponent);
        aircraft2101.setAirframe(f35AirframeComponent);
        aircraft2101.setEjectionSeat(f35EjectionSeatComponent);
        aircraft2101.setVerticalLiftSystem(f35VerticalLiftSystemComponent);

        // ------------------------------------------------------------------------------------------------
        // TODO: Install the parts.
        // ------------------------------------------------------------------------------------------------
    }

    private static IRI iri() {
        return new IRI(TEST_BASE, UUID.randomUUID().toString());
    }
}
