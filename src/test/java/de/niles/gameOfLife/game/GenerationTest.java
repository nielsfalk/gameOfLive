package de.niles.gameOfLife.game;

import org.junit.Test;

import static de.niles.gameOfLife.game.CellTest.Alive.alive;
import static de.niles.gameOfLife.game.CellTest.Dead.dead;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class GenerationTest {

    @Test
    public void initializer() {
        Generation generation = new Generation(//
                "" + //
                        "XX\n" + //
                        "XX\n" + //
                        "XXX\n");
        assertThat(generation.getCellAtContinuing(0, 0).countLivingNeighbors(), is(3));
        assertThat(generation.getCellAtContinuing(1, 1).countLivingNeighbors(), is(6));
        assertThat(generation.getCellAtContinuing(2, 2).countLivingNeighbors(), is(2));
    }

    @Test
    public void rul1() {
        Generation generation = new Generation(//
                "" + //
                        "\n" + //
                        "\n" + //
                        "XXX\n");
        assertThat(generation.getCellAtContinuing(1, 1).countLivingNeighbors(), is(3));
        Generation nextGeneration = generation.next();
        assertThat(generation == nextGeneration, is(false));
        assertThat(nextGeneration.getCellAtContinuing(1, 1), is(alive()));
    }

    @Test
    public void rul2() {
        Generation generation = new Generation(//
                "" + //
                        "\n" + //
                        " X\n" + //
                        " X\n");
        assertThat(generation.getCellAtContinuing(1, 1).countLivingNeighbors(), is(1));
        Generation nextGeneration = generation.next();
        assertThat(generation == nextGeneration, is(false));
        assertThat(nextGeneration.getCellAtContinuing(1, 1), is(dead()));
    }

    @Test
    public void constantEnd() {
        for (String[] constantEnd : constantEnds) {
            Generation generation = new Generation(constantEnd[0]);

            for (int i = 0; i < constantEnd.length - 1; i++) {
                assertThat(generation.getNumberOfGeneration(), is(i));
                assertThat(new Generation(constantEnd[i]), equalTo(generation));
                assertThat(generation.next(), not(equalTo(generation)));
                generation = generation.next();
            }
            assertThat(generation, is(equalTo(generation.next())));
            assertThat(generation.next() == generation.next().next(), is(true));

            assertThat(generation.getNumberOfGeneration(), is(generation.next().getNumberOfGeneration() - 1));
            assertThat(generation.getNumberOfGeneration(), is(generation.next().next().getNumberOfGeneration() - 1));
        }
    }

    @Test
    public void flippingEnd() {
        for (String[] flippingEnd : flippingEnds) {
            Generation generation = new Generation(flippingEnd[0]);

            for (int i = 0; i < flippingEnd.length - 1; i++) {
                assertThat(generation.getNumberOfGeneration(), is(i));
                assertThat(new Generation(flippingEnd[i]), equalTo(generation));
                assertThat(generation.next(), not(equalTo(generation)));
                generation = generation.next();
            }
            assertThat(generation, is(equalTo(generation.next().next())));
            assertThat(generation.next() == generation.next().next().next(), is(true));

            assertThat(generation.getNumberOfGeneration(), is(generation.next().getNumberOfGeneration() - 1));
            assertThat(generation.next().getNumberOfGeneration(), is(generation.next().next().next()
                    .getNumberOfGeneration()));
        }
    }

    static String flippingEnds[][] = {{//
            " O \n" + //
                    " O \n" + //
                    " O \n"//
    }};

    static String constantEnds[][] = {{//

            "" + //
                    " O      \n" + //
                    "  O     \n" + //
                    "OOO     \n" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n"//


            , //

            "" + //
                    "        \n" + //
                    "O O     \n" + //
                    " OO     \n" + //
                    " O      \n" + //
                    "        \n" + //
                    "        \n"//

            ,//

            "" + //
                    "        \n" + //
                    "  O     \n" + //
                    "O O     \n" + //
                    " OO     \n" + //
                    "        \n" + //
                    "        \n"//

            ,//

            "" + //
                    "        \n" + //
                    " O      \n" + //
                    "  OO    \n" + //
                    " OO     \n" + //
                    "        \n" + //
                    "        \n"//

            ,//

            "" + //
                    "        \n" + //
                    "  O     \n" + //
                    "   O    \n" + //
                    " OOO    \n" + //
                    "        \n" + //
                    "        \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    " O O    \n" + //
                    "  OO    \n" + //
                    "  O     \n" + //
                    "        \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "   O    \n" + //
                    " O O    \n" + //
                    "  OO    \n" + //
                    "        \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "  O     \n" + //
                    "   OO   \n" + //
                    "  OO    \n" + //
                    "        \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "   O    \n" + //
                    "    O   \n" + //
                    "  OOO   \n" + //
                    "        \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "  O O   \n" + //
                    "   OO   \n" + //
                    "   O    \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "    O   \n" + //
                    "  O O   \n" + //
                    "   OO   \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "   O    \n" + //
                    "    OO  \n" + //
                    "   OO   \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "    O   \n" + //
                    "     O  \n" + //
                    "   OOO  \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "   O O  \n" + //
                    "    OO  \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "     O  \n" + //
                    "    OO  \n"//

            ,//

            "" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "        \n" + //
                    "    OO  \n" + //
                    "    OO  \n"//
    }, {
            //

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    " OOOO                      \n" + //
                    "O   O                      \n" + //
                    "    O                      \n" + //
                    "O  O                       \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "  OO                       \n" + //
                    " OOOO                      \n" + //
                    " OO OO                     \n" + //
                    "   OO                      \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    " O  O                      \n" + //
                    "     O                     \n" + //
                    " O   O                     \n" + //
                    "  OOOO                     \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "    OO                     \n" + //
                    "  OO OO                    \n" + //
                    "  OOOO                     \n" + //
                    "   OO                      \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "   OOOO                    \n" + //
                    "  O   O                    \n" + //
                    "      O                    \n" + //
                    "  O  O                     \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "    OO                     \n" + //
                    "   OOOO                    \n" + //
                    "   OO OO                   \n" + //
                    "     OO                    \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "   O  O                    \n" + //
                    "       O                   \n" + //
                    "   O   O                   \n" + //
                    "    OOOO                   \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "      OO                   \n" + //
                    "    OO OO                  \n" + //
                    "    OOOO                   \n" + //
                    "     OO                    \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "     OOOO                  \n" + //
                    "    O   O                  \n" + //
                    "        O                  \n" + //
                    "    O  O                   \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "      OO                   \n" + //
                    "     OOOO                  \n" + //
                    "     OO OO                 \n" + //
                    "       OO                  \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "     O  O                  \n" + //
                    "         O                 \n" + //
                    "     O   O                 \n" + //
                    "      OOOO                 \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "        OO                 \n" + //
                    "      OO OO                \n" + //
                    "      OOOO                 \n" + //
                    "       OO                  \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "       OOOO                \n" + //
                    "      O   O                \n" + //
                    "          O                \n" + //
                    "      O  O                 \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "        OO                 \n" + //
                    "       OOOO                \n" + //
                    "       OO OO               \n" + //
                    "         OO                \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "       O  O                \n" + //
                    "           O               \n" + //
                    "       O   O               \n" + //
                    "        OOOO               \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "          OO               \n" + //
                    "        OO OO              \n" + //
                    "        OOOO               \n" + //
                    "         OO                \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "         OOOO              \n" + //
                    "        O   O              \n" + //
                    "            O              \n" + //
                    "        O  O               \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "          OO               \n" + //
                    "         OOOO              \n" + //
                    "         OO OO             \n" + //
                    "           OO              \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "         O  O              \n" + //
                    "             O             \n" + //
                    "         O   O             \n" + //
                    "          OOOO             \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "            OO             \n" + //
                    "          OO OO            \n" + //
                    "          OOOO             \n" + //
                    "           OO              \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "           OOOO            \n" + //
                    "          O   O            \n" + //
                    "              O            \n" + //
                    "          O  O             \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "            OO             \n" + //
                    "           OOOO            \n" + //
                    "           OO OO           \n" + //
                    "             OO            \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "           O  O            \n" + //
                    "               O           \n" + //
                    "           O   O           \n" + //
                    "            OOOO           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "              OO           \n" + //
                    "            OO OO          \n" + //
                    "            OOOO           \n" + //
                    "             OO            \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "             OOOO          \n" + //
                    "            O   O          \n" + //
                    "                O          \n" + //
                    "            O  O           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "              OO           \n" + //
                    "             OOOO          \n" + //
                    "             OO OO         \n" + //
                    "               OO          \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "             O  O          \n" + //
                    "                 O         \n" + //
                    "             O   O         \n" + //
                    "              OOOO         \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                OO         \n" + //
                    "              OO OO        \n" + //
                    "              OOOO         \n" + //
                    "               OO          \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "               OOOO        \n" + //
                    "              O   O        \n" + //
                    "                  O        \n" + //
                    "              O  O         \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                OO         \n" + //
                    "               OOOO        \n" + //
                    "               OO OO       \n" + //
                    "                 OO        \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "               O  O        \n" + //
                    "                   O       \n" + //
                    "               O   O       \n" + //
                    "                OOOO       \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                  OO       \n" + //
                    "                OO OO      \n" + //
                    "                OOOO       \n" + //
                    "                 OO        \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                 OOOO      \n" + //
                    "                O   O      \n" + //
                    "                    O      \n" + //
                    "                O  O       \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                  OO       \n" + //
                    "                 OOOO      \n" + //
                    "                 OO OO     \n" + //
                    "                   OO      \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                 O  O      \n" + //
                    "                     O     \n" + //
                    "                 O   O     \n" + //
                    "                  OOOO     \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                    OO     \n" + //
                    "                  OO OO    \n" + //
                    "                  OOOO     \n" + //
                    "                   OO      \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                   OOOO    \n" + //
                    "                  O   O    \n" + //
                    "                      O    \n" + //
                    "                  O  O     \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                    OO     \n" + //
                    "                   OOOO    \n" + //
                    "                   OO OO   \n" + //
                    "                     OO    \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                   O  O    \n" + //
                    "                       O   \n" + //
                    "                   O   O   \n" + //
                    "                    OOOO   \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                      OO   \n" + //
                    "                    OO OO  \n" + //
                    "                    OOOO   \n" + //
                    "                     OO    \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                     OOOO  \n" + //
                    "                    O   O  \n" + //
                    "                        O  \n" + //
                    "                    O  O   \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                      OO   \n" + //
                    "                     OOOO  \n" + //
                    "                     OO OO \n" + //
                    "                       OO  \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                     O  O  \n" + //
                    "                         O \n" + //
                    "                     O   O \n" + //
                    "                      OOOO \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                        OO \n" + //
                    "                      OO OO\n" + //
                    "                      OOOO \n" + //
                    "                       OO  \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                       OOOO\n" + //
                    "                      O   O\n" + //
                    "                          O\n" + //
                    "                      O  O \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                        OO \n" + //
                    "                       OOOO\n" + //
                    "                       OO O\n" + //
                    "                         OO\n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                       O  O\n" + //
                    "                          O\n" + //
                    "                       O   \n" + //
                    "                        OOO\n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                        O O\n" + //
                    "                        OO \n" + //
                    "                         O \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                        O  \n" + //
                    "                        O O\n" + //
                    "                        OO \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                         O \n" + //
                    "                       OO  \n" + //
                    "                        OO \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                        O  \n" + //
                    "                       O   \n" + //
                    "                       OOO \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                       O O \n" + //
                    "                       OO  \n" + //
                    "                        O  \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                       O   \n" + //
                    "                       O O \n" + //
                    "                       OO  \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                        O  \n" + //
                    "                      OO   \n" + //
                    "                       OO  \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                       O   \n" + //
                    "                      O    \n" + //
                    "                      OOO  \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                      O O  \n" + //
                    "                      OO   \n" + //
                    "                       O   \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                      O    \n" + //
                    "                      O O  \n" + //
                    "                      OO   \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                       O   \n" + //
                    "                     OO    \n" + //
                    "                      OO   \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                      O    \n" + //
                    "                     O     \n" + //
                    "                     OOO   \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                     O O   \n" + //
                    "                     OO    \n" + //
                    "                      O    \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                     O     \n" + //
                    "                     O O   \n" + //
                    "                     OO    \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                      O    \n" + //
                    "                    OO     \n" + //
                    "                     OO    \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                     O     \n" + //
                    "                    O      \n" + //
                    "                    OOO    \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                    O O    \n" + //
                    "                    OO     \n" + //
                    "                     O     \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                    O      \n" + //
                    "                    O O    \n" + //
                    "                    OO     \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                     O     \n" + //
                    "                   OO      \n" + //
                    "                    OO     \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                    O      \n" + //
                    "                   O       \n" + //
                    "                   OOO     \n" + //
                    "                           \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                   O O     \n" + //
                    "                   OO      \n" + //
                    "                    O      \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                   O       \n" + //
                    "                   O O     \n" + //
                    "                   OO      \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                    O      \n" + //
                    "                  OO       \n" + //
                    "                   OO      \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                   O       \n" + //
                    "                  O        \n" + //
                    "                  OOO      \n" + //
                    "                           \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                  O O      \n" + //
                    "                  OO       \n" + //
                    "                   O       \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                  O        \n" + //
                    "                  O O      \n" + //
                    "                  OO       \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                   O       \n" + //
                    "                 OO        \n" + //
                    "                  OO       \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                  O        \n" + //
                    "                 O         \n" + //
                    "                 OOO       \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                 O O       \n" + //
                    "                 OO        \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                 O         \n" + //
                    "                 OO        \n"//


            ,//

            "" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                           \n" + //
                    "                 OO        \n" + //
                    "                 OO        \n"//
    }};
}
