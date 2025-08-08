package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35AirframeComponent interface.
 */
public class F35AirframeComponentImpl extends HqdmObject implements F35AirframeComponent {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F35AirframeComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F35_AIRFRAME_COMPONENT_TYPE_IRI);
    }
}
