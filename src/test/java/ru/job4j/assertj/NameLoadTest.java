package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkMassageValidateButNotSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "dgdvd";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    void checkMassageValidateButStartSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "=dgdvd";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkMassageValidateButFinishSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "dgdvd=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("does not contain a value");
    }

    @Test
    void checkMassageValidateButNullSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "";
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("Names array is empty");
    }
}