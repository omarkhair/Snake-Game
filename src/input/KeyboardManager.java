package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {
	private boolean[] key;
	private boolean up, down, left, right;

	public KeyboardManager() {
		key= new boolean[256];
	}
	
	public void tick() {
		up=key[KeyEvent.VK_UP];
		down=key[KeyEvent.VK_DOWN];
		left=key[KeyEvent.VK_LEFT];
		right=key[KeyEvent.VK_RIGHT];
	}
	@Override
	public void keyPressed(KeyEvent e) {
		key[e.getKeyCode()]=true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key[e.getKeyCode()]=false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}
}
