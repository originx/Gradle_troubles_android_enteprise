package espresso.fail.multidex;/*
 * The MIT License
 * 
 * Copyright (c) 2016 Andreas Ahlenstorf
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RxIdlingResource implements IdlingResource, io.reactivex.functions
        .Function<Runnable, Runnable> {

    private static final String RESOURCE_NAME = RxIdlingResource.class.getSimpleName();

    private static final ReentrantReadWriteLock IDLING_STATE_LOCK = new ReentrantReadWriteLock();

    // Guarded by IDLING_STATE_LOCK
    private int taskCount = 0;

    // Guarded by IDLING_STATE_LOCK
    private ResourceCallback transitionCallback;

    @Override
    public String getName() {
        return RESOURCE_NAME;
    }

    @Override
    public boolean isIdleNow() {
        boolean result;

        IDLING_STATE_LOCK.readLock().lock();
        result = taskCount == 0;
        IDLING_STATE_LOCK.readLock().unlock();

        return result;
    }

    @Override
    public void registerIdleTransitionCallback(final ResourceCallback callback) {
        IDLING_STATE_LOCK.writeLock().lock();
        this.transitionCallback = callback;
        IDLING_STATE_LOCK.writeLock().unlock();
    }

    @Override
    public Runnable apply(final Runnable runnable) {
        return new Runnable() {
            @Override
            public void run() {
                IDLING_STATE_LOCK.writeLock().lock();
                taskCount++;
                IDLING_STATE_LOCK.writeLock().unlock();

                try {
                    runnable.run();
                } finally {
                    IDLING_STATE_LOCK.writeLock().lock();

                    try {
                        taskCount--;

                        if (taskCount == 0 && transitionCallback != null) {
                            transitionCallback.onTransitionToIdle();
                        }
                    } finally {
                        IDLING_STATE_LOCK.writeLock().unlock();
                    }
                }
            }
        };
    }
}