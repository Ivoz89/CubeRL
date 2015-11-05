package control



/**
 * Created by iozi on 04/11/2015.
 */
case class Direction (val dy : Int, val dx : Int)
object NORTH extends Direction(-1,0)
object SOUTH extends Direction(1,0)
object WEST extends Direction(0,-1)
object EAST extends Direction(0,1)
object NORTHWEST extends Direction(-1,-1)
object NORTHEAST extends Direction(-1,1)
object SOUTHWEST extends Direction(1,-1)
object SOUTHEAST extends Direction(1,1)