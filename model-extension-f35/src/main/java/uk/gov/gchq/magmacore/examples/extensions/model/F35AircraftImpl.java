package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35Aircraft interface.
 */
public class F35AircraftImpl extends HqdmObject implements F35Aircraft {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F35AircraftImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F35_AIRCRAFT_TYPE_IRI);
    }
}
