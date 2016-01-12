package sprite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Animation implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 176588250271760395L;
		private int current;
		private boolean repeat, isDone;
		private List<Integer> frames;
		
		public Animation(boolean repeat, List<Integer> frames) {
			this.repeat = repeat;
			this.frames = frames;
		}
		
		public Animation(boolean repeat) {
			this(repeat, new ArrayList<>());
		}
		
		public Animation(boolean repeat, int... frames) {
			this(repeat);
			for (int i : frames) {
				addFrame(i);
			}
		}
		
		public Animation(int... frames) {
			this(true, frames);
		}
		
		public void addFrame(int frame) {
			frames.add(frame);
		}

		/**
		 * Returns the current frame.
		 * 
		 * @return The current frame
		 */
		public int getCurrent() {
			if (frames.size() > 0) {
				return frames.get(current);
			} else {
				return -1;
			}
		}

		/**
		 * Returns true if the current frame is less than the end frame.
		 * 
		 * @return Whether or not the animation is complete
		 */
		public boolean isDone() {
			return isDone;
		}

		public void restart() {
			current = 0;
			isDone  = false;
		}
		
		public boolean isRepeating() {
			return repeat;
		}

		/**
		 * Increments the current frame if the animation is not complete.
		 */
		public void update() {
			if (!isDone || repeat) {
				current++;
				isDone = current >= frames.size();
				if (isDone) {
					current = 0;
				}
			}
		}
		
		public Animation clone() {
			return new Animation(repeat, frames);
		}
		
		public void clear() {
			frames.clear();
		}
		
		public List<Integer> getFrames() {
			return frames;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[repeat:").append(repeat).append(" frames:");
			sb.append(frames);
			sb.append("]");
			
			return sb.toString();
		}
		
		public void setReapeat(boolean repeat) {
			this.repeat = repeat;
		}
	}