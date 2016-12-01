package cz.uhk.pro2.flappy.game;

/**
 * Rozhrani pro objekty, ktere potrebuji vedet kolik casu (ticku) ubehlo od
 * zacatku hry
 * 
 * @author konvima1
 *
 */
public interface TickAware {
	/**
	 * Zmeni stav herni entity s ohledem na zmenu herniho casu
	 * @param ticksSinceStart cas (pocet "ticku") od zahajeni hry
	 */
	void tick(long ticksSinceStart);

}
