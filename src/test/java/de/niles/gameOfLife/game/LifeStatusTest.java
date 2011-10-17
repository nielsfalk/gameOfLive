package de.niles.gameOfLife.game;


import org.junit.Test;

import static de.niles.gameOfLife.game.LifeStatus.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LifeStatusTest {

    private Generation generation;

    @Test
    public void szenario() {
        generation = new Generation(GenerationTest.constantEnds[0][0]);
        assertThat(getLifeStatusAt(0, 0), is(initialDead));
        assertThat(getLifeStatusAt(0, 1), is(initialAlive));
        assertThat(getLifeStatusAt(0, 2), is(initialDead));

        assertThat(getLifeStatusAt(1, 0), is(initialDead));
        assertThat(getLifeStatusAt(1, 1), is(initialDead));
        assertThat(getLifeStatusAt(1, 2), is(initialAlive));

        assertThat(getLifeStatusAt(2, 0), is(initialAlive));
        assertThat(getLifeStatusAt(2, 1), is(initialAlive));
        assertThat(getLifeStatusAt(2, 2), is(initialAlive));

        generation = generation.next();

        assertThat(getLifeStatusAt(0, 0), is(dead));
        assertThat(getLifeStatusAt(0, 1), is(dieing));
        assertThat(getLifeStatusAt(0, 2), is(dead));

        assertThat(getLifeStatusAt(1, 0), is(born));
        assertThat(getLifeStatusAt(1, 1), is(dead));
        assertThat(getLifeStatusAt(1, 2), is(living));

        assertThat(getLifeStatusAt(2, 0), is(dieing));
        assertThat(getLifeStatusAt(2, 1), is(living));
        assertThat(getLifeStatusAt(2, 2), is(living));


    }

    private LifeStatus getLifeStatusAt(int row, int pos) {
        return generation.getCellAtContinuing(pos, row).getLifeStatus();
    }

}
