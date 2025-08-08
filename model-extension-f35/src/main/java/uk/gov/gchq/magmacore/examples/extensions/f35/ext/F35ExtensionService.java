package uk.gov.gchq.magmacore.examples.extensions.f35.ext;

import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_ENGINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_ENGINE_SYSTEM_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_ENGINE_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_TURBINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_TURBINE_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_AIRCRAFT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_AIRFRAME_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_AIRFRAME_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_EJECTION_SEAT_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_ENGINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_TURBINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.US16E_EJECTION_SEAT_TYPE_NAME;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135EngineImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135EngineSystemImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135TurbineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135TurbineImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135VerticalLiftSystemComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135VerticalLiftSystemImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35AircraftImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35AirframeComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35AirframeImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EjectionSeatComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EngineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35TurbineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35VerticalLiftSystemComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.US16EEjectionSeatImpl;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionService;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An example HQDM ExtensionService for adding some extension classes to
 * MagmaCore.
 */
public class F35ExtensionService implements ExtensionService {
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Thing> T createEntity(final String typeName, final IRI iri) {

        switch (typeName) {
            case F135_ENGINE_COMPONENT_TYPE_NAME:
                return (T) new F35EngineComponentImpl(iri);

            case F135_ENGINE_SYSTEM_TYPE_NAME:
                return (T) new F135EngineSystemImpl(iri);

            case F135_ENGINE_TYPE_NAME:
                return (T) new F135EngineImpl(iri);

            case F135_TURBINE_COMPONENT_TYPE_NAME:
                return (T) new F135TurbineComponentImpl(iri);

            case F135_TURBINE_TYPE_NAME:
                return (T) new F135TurbineImpl(iri);

            case F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME:
                return (T) new F135VerticalLiftSystemComponentImpl(iri);

            case F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME:
                return (T) new F135VerticalLiftSystemImpl(iri);

            case F35_AIRFRAME_COMPONENT_TYPE_NAME:
                return (T) new F35AirframeComponentImpl(iri);

            case F35_AIRCRAFT_TYPE_NAME:
                return (T) new F35AircraftImpl(iri);

            case F35_AIRFRAME_TYPE_NAME:
                return (T) new F35AirframeImpl(iri);

            case F35_EJECTION_SEAT_COMPONENT_TYPE_NAME:
                return (T) new F35EjectionSeatComponentImpl(iri);

            case F35_ENGINE_COMPONENT_TYPE_NAME:
                return (T) new F35EjectionSeatComponentImpl(iri);

            case F35_TURBINE_COMPONENT_TYPE_NAME:
                return (T) new F35TurbineComponentImpl(iri);

            case F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME:
                return (T) new F35VerticalLiftSystemComponentImpl(iri);

            case US16E_EJECTION_SEAT_TYPE_NAME:
                return (T) new US16EEjectionSeatImpl(iri);

            default:
                // Or null if it's not a class from this extension package.
                return null;
        }
    }
}
