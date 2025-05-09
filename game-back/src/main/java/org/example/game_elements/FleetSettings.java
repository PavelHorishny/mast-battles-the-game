package org.example.game_elements;

import lombok.Getter;

@Getter
public enum FleetSettings {
    STANDARD_FLEET(
            new FortificationType[]{
                    FortificationType.ROYAL_PORT,
                    FortificationType.FIRST_LINE_FORT,
                    FortificationType.FIRST_LINE_FORT,
                    FortificationType.FIRST_LINE_FORT,
                    FortificationType.SECOND_LINE_FORT,
                    FortificationType.SECOND_LINE_FORT,
                    FortificationType.SECOND_LINE_FORT,
            },
            new VesselType[]{
                    VesselType.THREE_DECKER_SHIP_OF_LINE,
                    VesselType.THREE_DECKER_SHIP_OF_LINE,
                    VesselType.TWO_DECKER_SHIP_OF_LINE,
                    VesselType.TWO_DECKER_SHIP_OF_LINE,
                    VesselType.TWO_DECKER_SHIP_OF_LINE,
                    VesselType.TWO_DECKER_SHIP_OF_LINE,
                    VesselType.FRIGATE,
                    VesselType.FRIGATE,
                    VesselType.FRIGATE,
                    VesselType.TENDER,
                    VesselType.TENDER,
                    VesselType.BRIG,
                    VesselType.BRIG,
                    VesselType.BRIG,
                    VesselType.BRIG,
                    VesselType.BRIG,
                    VesselType.GALLEON,
                    VesselType.GALLEON,
                    VesselType.GALLEON,
                    VesselType.STEAM_FRIGATE,
                    VesselType.BATTERY,
                    VesselType.CORVETTE,
                    VesselType.MONITOR,
                    VesselType.STEAMSHIP,
            }),
    ;

    private final FortificationType [] fortifications;
    private final VesselType [] vessels;

    FleetSettings(FortificationType[] fortifications, VesselType[] vessels) {
        this.fortifications = fortifications;
        this.vessels = vessels;
    }
}
