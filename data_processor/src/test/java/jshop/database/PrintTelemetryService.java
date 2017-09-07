package jshop.database;

import jshop.services.TelemetryService;

public class PrintTelemetryService implements TelemetryService {
    @Override
    public void doTelemetry(Exception e) {
        System.out.println("EXCEPTION: "+e);
    }
}
