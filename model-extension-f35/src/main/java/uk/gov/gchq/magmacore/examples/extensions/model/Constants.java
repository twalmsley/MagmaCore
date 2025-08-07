package uk.gov.gchq.magmacore.examples.extensions.model;

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.HQDM;

import uk.gov.gchq.magmacore.hqdm.rdf.iri.HqdmIri;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * Define some useful Constants.
 */
public interface Constants {

    public static final String F35_AIRFRAME_TYPE_NAME = "F35Airframe";

    public static final IRI F35_AIRFRAME_TYPE_IRI = new HqdmIri(HQDM, F35_AIRFRAME_TYPE_NAME);

    public static final String F135_ENGINE_TYPE_NAME = "F135Engine";

    public static final IRI F135_ENGINE_TYPE_IRI = new HqdmIri(HQDM, F135_ENGINE_TYPE_NAME);

    public static final String US16E_EJECTION_SEAT_TYPE_NAME = "US16EEjectionSeat";

    public static final IRI US16E_EJECTION_SEAT_TYPE_IRI = new HqdmIri(HQDM, US16E_EJECTION_SEAT_TYPE_NAME);

    public static final String F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME = "F135VerticalLiftSystem";

    public static final IRI F135_VERTICAL_LIFT_SYSTEM_TYPE_IRI = new HqdmIri(HQDM, F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME);

    public static final String F135_TURBINE_TYPE_NAME = "F135Turbine";

    public static final IRI F135_TURBINE_TYPE_IRI = new HqdmIri(HQDM, F135_TURBINE_TYPE_NAME);
}
