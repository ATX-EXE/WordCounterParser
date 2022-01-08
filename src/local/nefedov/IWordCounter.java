package local.nefedov;

public interface IWordCounter {
    /**
     * Event - Returns the number of words in a file or prepared snippet for a listener
     *
     * @param sender Listener
     * @param size Returns the number of words in a file or prepared breakout
     */
    void event(Object sender, int size);
}
