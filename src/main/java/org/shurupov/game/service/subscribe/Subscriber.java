package org.shurupov.game.service.subscribe;

public interface Subscriber {

    default void onInit() {};

    default void onBeforeUpdate() {}
    default void onUpdate() {}
    default void onRender() {}

    default void onAfterUpdate() {}

    default void onKeyPressed(int key) {}
}
