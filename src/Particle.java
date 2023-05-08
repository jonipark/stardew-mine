import GaFr.GFStamp;
import java.util.*;
import java.lang.*;
import GaFr.GFU;

public class Particle {

	/*

	Creates a short-lived particle effect. 
	The particle is customizable, and self-terminates. 
	Current constraints are that movement direction and alpha
	fadeout are both linear. 

	*/


	/*
	 * Engine component -- controls particles
	 */
	
	public static ArrayList<Particle> particles = new ArrayList<Particle>();

	// A particle effect where particles of rock explode out of x,y.
	public static void newRockSmash(int x, int y) {
		int qty = GFU.randint(3,5);

		for (int i = 0; i < qty; i++) {
			new Particle(TextureEntry.get("MINERAL_PARTICLE"), x+GFU.randint(-6,6), y+GFU.randint(-6,6), ((double)GFU.randint(-5,5)) / 2, ((double)GFU.randint(-5,5)) / 2, 1.0, 10);
		}
		
	}

	// Draws all particles. Also updates them, to reduce iteration load.
	public static void drawParticles() {
		if (particles.size() != 0) {
			for (int p = 0; p < particles.size(); p++) {
				Particle particle = particles.get(p);
				particle.update();
				particle.draw();
			}
		}
	}

	public GFStamp img;
	public double xvel;
	public double yvel;
	public int x;
	public int y;
	public double drag;
	public double alpha;
	public int timer;
	public int fullLifetime;

	/*
		nimg = the integer id of the particle's image
		nx = the origin x of the particle
		ny = the origin y of the particle
		velx = the x vector of the particle's direction
		vely = the y vector of the particle's direction
		ndrag = drag exerted on the velocity. 
			1.0 is no change, 0.5 is high drag, and 1.5 is fast acceleration.
		nlifetime = lifetime, in frames
	*/

	public Particle(GFStamp nimg, int nx, int ny, double velx, double vely, double ndrag, int nlifetime) {

		img = nimg;
		x = nx;
		y = ny;
		xvel = velx;
		yvel = vely;
		drag = ndrag;
		timer = nlifetime;
		fullLifetime = nlifetime;

		particles.add(this);
	}


	// Updates the particle's attributes. Should be called per frame.
	public void update() {

		this.timer--;

		if (this.timer == 0) { // delete thyself
			particles.remove(this);
			return;
		} 

		this.xvel = xvel*drag;
		this.yvel = yvel+0.3f;

		this.x += xvel;
		this.y += yvel;

		// linear fade-out -- a bit of easing!
		this.alpha = timer / (double)fullLifetime;
		
	}

	// Draws the particle.
	public void draw() {

		img.moveTo(x-Game.cameraX, y-Game.cameraY);
		img.setAlpha(alpha);
		img.stamp();

	}
}