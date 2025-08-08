package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF135EngineImpl.
 */
public class StateOfF135EngineImpl extends HqdmObject implements StateOfF135Engine {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF135EngineImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F135_ENGINE_TYPE_IRI);
    }
}
