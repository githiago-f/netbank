package com.netbank.common.bank.accounts;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.netbank.common.events.BaseEvent;

public abstract class AggregateRoot {
    protected String id;
    private Integer version = -1;
    private final List<BaseEvent> changes = new ArrayList<>();
    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void markChangesAsCommited() {
        this.changes.clear();
    }

    public List<BaseEvent> getUncommitedChanges() {
        return Collections.unmodifiableList(this.changes);
    }

    protected void applyChange(BaseEvent event, Boolean isNewEvent) {
        try {
            Method method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException nsm) {
            logger.log(Level.WARNING, "No such method apply was found on the aggregate for {0}", event.getClass().getName());
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Error applying event to aggregate {0}", e.getMessage());
        } finally {
            if(isNewEvent) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChange(event, false));
    }
}
