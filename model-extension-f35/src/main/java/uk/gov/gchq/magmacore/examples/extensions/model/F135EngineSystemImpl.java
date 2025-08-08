package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F135EngineSystem interface.
 */
public class F135EngineSystemImpl extends HqdmObject implements F135EngineSystem {
    private F35EngineComponent engine;
    private F35TurbineComponent turbine;
    private F35VerticalLiftSystemComponent vls;

    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F135EngineSystemImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F135_ENGINE_SYSTEM_TYPE_IRI);
    }

    @Override
    public F35EngineComponent getEngine() {
        return engine;
    }

    @Override
    public F35TurbineComponent getTurbine() {
        return turbine;
    }

    @Override
    public F35VerticalLiftSystemComponent getVls() {
        return vls;
    }

    @Override
    public void setEngine(final F35EngineComponent engine) {
        this.engine = engine;
        engine.addValue(HQDM.COMPONENT_OF, this.getId());
    }

    @Override
    public void setTurbine(final F35TurbineComponent turbine) {
        this.turbine = turbine;
        turbine.addValue(HQDM.COMPONENT_OF, this.getId());
    }

    @Override
    public void setVerticalLiftSystem(final F35VerticalLiftSystemComponent vls) {
        this.vls = vls;
        vls.addValue(HQDM.COMPONENT_OF, this.getId());
    }
}
