package cc.co.evenprime.bukkit.nocheat.data;

import org.bukkit.Location;


/**
 * Playerspecific data for the blockbreak check group
 * 
 * @author Evenprime
 * 
 */
public class BlockBreakData extends Data {

    public double              reachViolationLevel     = 0.0D;
    public double              directionViolationLevel = 0.0D;

    public Location            instaBrokeBlockLocation = null;
    public final ActionData history                 = new ActionData();

}
