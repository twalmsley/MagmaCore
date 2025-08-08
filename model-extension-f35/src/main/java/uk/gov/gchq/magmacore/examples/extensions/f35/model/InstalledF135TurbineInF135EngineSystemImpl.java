package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An InstalledF135TurbineInF135EngineSystemImpl.
 */
public class InstalledF135TurbineInF135EngineSystemImpl extends HqdmObject implements InstalledFunctionalSystemComponent, StateOfF135Turbine, StateOfF135TurbineComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public InstalledF135TurbineInF135EngineSystemImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.INSTALLED_F135_TURBINE_IN_F135_ENGINE_SYSTEM_TYPE_IRI);
    }
}
