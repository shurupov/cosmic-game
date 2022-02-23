package org.shurupov.game.service.subscribe;


import java.util.ArrayList;
import java.util.List;

public class GameEventPublisher {

    private final List<Subscriber> subscribers;

    public GameEventPublisher() {
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void init() {
        for (Subscriber subscriber : subscribers) {
            try {
                subscriber.onInit();
            } catch (Throwable e) {
                System.out.println(e);
            }
        }
    }

    public void beforeUpdate() {
        for (Subscriber subscriber : subscribers) {
            try {
                subscriber.onBeforeUpdate();
            } catch (Throwable e) {
                System.out.println(e);
            }
        }
    }

    public void update() {
        for (Subscriber subscriber : subscribers) {
            try {
                subscriber.onUpdate();
            } catch (Throwable e) {
                System.out.println(e);
            }
        }
    }

    public void render() {
        for (Subscriber subscriber : subscribers) {
            try {
                subscriber.onRender();
            } catch (Throwable e) {
                System.out.println(e);
            }
        }
    }

    public void afterUpdate() {
        for (Subscriber subscriber : subscribers) {
            try {
                subscriber.onAfterUpdate();
            } catch (Throwable e) {
                System.out.println(e);
            }
        }
    }

    public void keyPressed(int key) {
        for (Subscriber subscriber : subscribers) {
            try {
                subscriber.onKeyPressed(key);
            } catch (Throwable e) {
                System.out.println(e);
            }
        }
    }
}
