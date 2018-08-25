package com.ai.spring.cloud.auth;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestClone {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNotCloneable() {
        NotCloneable notCloneable = new NotCloneable();
        NotCloneable clone = null;
        try {
            clone = (NotCloneable) notCloneable.clone();
        } catch (CloneNotSupportedException e) {
            Assert.assertNotNull(e);
        }
        Assert.assertNull(clone);
    }

    @Test
    public void testCloneable() throws CloneNotSupportedException {
        AbleToClone cloneable = new AbleToClone();
        cloneable.name = "clone";
        AbleToClone clone = (AbleToClone) cloneable.clone();
        Assert.assertEquals(clone.name, cloneable.name);
    }

    @Test
    public void testLightClone() throws CloneNotSupportedException {
        LightClone lightClone = new LightClone();
        lightClone.name = "my new name";
        lightClone.b = new int[2];
        lightClone.b[0] = 1;
        lightClone.b[1] = 2;

        LightClone clone = (LightClone) lightClone.clone();
        Assert.assertEquals(lightClone.name, clone.name);
        Assert.assertNull(clone.b);
    }

    public class NotCloneable {

        String name = "i am not cloneable";

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public class AbleToClone implements Cloneable {

        String name = "i am cloneable";

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public class LightClone implements Cloneable {
        int[] b;
        String name;

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }


}
