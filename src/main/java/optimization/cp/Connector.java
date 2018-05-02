package optimization.cp;

import java.util.Collection;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;

public class Connector<E> {

    private Optional<E> value;
    private Object informant;
    private final Collection<Constraint<E>> constraints = Sets.newLinkedHashSet();

    public Connector() {
        this.value = Optional.absent();
    }

    public Connector(Object informant, E value) {
        setValue(informant, value);
    }

    public boolean hasValue() {
        return value.isPresent();
    }

    public final void forgetValue(Object retractor) {
        if (retractor == informant) {
            this.value = Optional.absent();
            informAboutForget(retractor);
        }
    }

    private void informAboutForget(Object retractor) {
        for (Constraint<E> constraint : constraints) {
            if (retractor != constraint) {
                constraint.informAboutForget(this);
            }
        }
    }

    public final void setValue(Object informant, E value) {
        if (!hasValue()) {
            this.informant = informant;
            this.value = Optional.of(value);
            informAboutNewValue(informant);
        } else if (!value.equals(this.value.get())) {
            throw new IllegalArgumentException("contradiction");
        }
    }

    private void informAboutNewValue(Object informant) {
        for (Constraint<E> constraint : constraints) {
            if (informant != constraint) {
                constraint.informAboutNewValue(this);
            }
        }
    }

    public E getValue() {
        return value.get();
    }

    public void addConstraint(Constraint<E> constraint) {
        constraints.add(constraint);

        if (hasValue()) {
            constraint.informAboutNewValue(this);
        }
    }

    public void removeConstraint(Constraint<?> constraint) {
        constraints.remove(constraint);
    }

}
