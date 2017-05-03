package xion.node;

import xion.Game;

/**
 * The state of our {@link SpaceNode}.
 * world in this context stands for the our {@link SpaceNode} list in {@link Game}.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public enum NodeState {
	
	/**
	 * The {@link SpaceNode} is currently alive in the world.
	 */
	ALIVE,
	
	/**
	 * The {@link SpaceNode} is dead in our world.
	 */
	DEAD
	
}
