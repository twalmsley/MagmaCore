package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An InstalledF135EngineInF135EngineSystemImpl.
 */
public class InstalledF135EngineInF135EngineSystemImpl extends HqdmObject implements InstalledFunctionalSystemComponent, StateOfF135Engine, StateOfF135EngineComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public InstalledF135EngineInF135EngineSystemImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.INSTALLED_F135_ENGINE_IN_F135_ENGINE_SYSTEM_TYPE_IRI);
    }
}
