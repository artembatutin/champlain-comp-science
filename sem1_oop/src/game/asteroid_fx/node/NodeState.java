package game.asteroid_fx.node;

import game.asteroid_fx.SpaceCraft;

/**
 * The state of our {@link SpaceNode}.
 * world in this context stands for the our {@link SpaceNode} list in {@link SpaceCraft}.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public enum NodeState {
	
	/**
	 * The {@link SpaceNode} is created but was never added to the list.
	 */
	CREATED,
	
	/**
	 * The {@link SpaceNode} is currently alive in the world.
	 */
	ALIVE,
	
	/**
	 * The {@link SpaceNode} is dead in our world.
	 */
	DEAD,
	
	/**
	 * The {@link SpaceNode} is not in ur world list.
	 */
	REMOVED
	
}
