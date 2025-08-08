package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystem;

/**
 * F135 Engine System.
 */
public interface F135EngineSystem extends FunctionalSystem {

    public F35EngineComponent getEngine();

    public F35TurbineComponent getTurbine();

    public F35VerticalLiftSystemComponent getVls();

    void setEngine(final F35EngineComponent engine);

    void setTurbine(final F35TurbineComponent turbine);

    void setVerticalLiftSystem(final F35VerticalLiftSystemComponent vls);
}
