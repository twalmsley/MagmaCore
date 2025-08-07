package uk.gov.gchq.magmacore.examples.extensions.ext;

import java.util.Map;

import uk.gov.gchq.magmacore.examples.extensions.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.model.F135Engine;
import uk.gov.gchq.magmacore.examples.extensions.model.F135VerticalLiftSystem;
import uk.gov.gchq.magmacore.examples.extensions.model.F35Airframe;
import uk.gov.gchq.magmacore.examples.extensions.model.US16EEjectionSeat;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionService;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionServiceProvider;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An example ExtensionServiceProvider for adding some extension classes to
 * MagmaCore.
 */
public class F35ExtensionServiceProvider implements ExtensionServiceProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public ExtensionService createService(final Map<IRI, Class<? extends Thing>> map) {

        // Register our extension IRIs and the corresponding interfaces.
        map.put(Constants.F135_VERTICAL_LIFT_SYSTEM_TYPE_IRI, F135VerticalLiftSystem.class);
        map.put(Constants.F135_ENGINE_TYPE_IRI, F135Engine.class);
        map.put(Constants.F35_AIRFRAME_TYPE_IRI, F35Airframe.class);
        map.put(Constants.US16E_EJECTION_SEAT_TYPE_IRI, US16EEjectionSeat.class);

        // Create ane return the extension service.
        return new F35ExtensionService();
    }
}
