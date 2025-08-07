package uk.gov.gchq.magmacore.examples.extensions.ext;

import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.F135_ENGINE_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.F135_TURBINE_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.F35_AIRFRAME_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.US16E_EJECTION_SEAT_TYPE_NAME;

import uk.gov.gchq.magmacore.examples.extensions.model.F135EngineImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.F135TurbineImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.F135VerticalLiftSystemImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.F35AirframeImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.US16EEjectionSeatImpl;
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
            case F135_ENGINE_TYPE_NAME:
                return (T) new F135EngineImpl(iri);

            case F135_TURBINE_TYPE_NAME:
                return (T) new F135TurbineImpl(iri);

            case F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME:
                return (T) new F135VerticalLiftSystemImpl(iri);

            case F35_AIRFRAME_TYPE_NAME:
                return (T) new F35AirframeImpl(iri);

            case US16E_EJECTION_SEAT_TYPE_NAME:
                return (T) new US16EEjectionSeatImpl(iri);

            default:
                // Or null if it's not a class from this extension package.
                return null;
        }
    }
}
