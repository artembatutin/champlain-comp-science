package game.battlefield.node;

import game.battlefield.BattleField;

/**
 * The state of our {@link Node}.
 * world in this context stands for the our {@link Node} list in {@link BattleField}.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public enum NodeState {
	
	/**
	 * The {@link Node} is currently alive in the world.
	 */
	ALIVE,
	
	/**
	 * The {@link Node} is dead in our world.
	 */
	DEAD
	
}
