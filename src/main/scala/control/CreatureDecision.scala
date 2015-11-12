package control

import control.action.CreatureAction
import model.Creature

/**
 * Ties Creature to the CreatureAction it chooses to perform
 * Created by iozi on 04/11/2015.
 */
class CreatureDecision(val creature : Creature, val action : CreatureAction)
