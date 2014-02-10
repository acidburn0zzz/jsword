/**
 * Distribution License:
 * JSword is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License, version 2.1 or later
 * as published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/lgpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2014
 *     The copyright to this program is held by it's authors.
 *
 */
package org.crosswire.jsword.versification;

import org.crosswire.jsword.versification.system.*;
import org.junit.Assert;
import org.crosswire.common.config.ConfigException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * JUnit Test
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author chrisburrell
 */
@RunWith(Parameterized.class)
public class FileVersificationMappingTest {
    private String v11nName;

    /**
     * @param v11nName the v11n name we are testing
     */
    public FileVersificationMappingTest(String v11nName) {
        this.v11nName = v11nName;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {SystemLeningrad.V11N_NAME},
                {SystemSynodal.V11N_NAME},
                {SystemVulg.V11N_NAME},
                {SystemGerman.V11N_NAME}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testVersifications() throws IOException, ConfigException {
        final Versification versification = Versifications.instance().getVersification(v11nName);
        FileVersificationMapping m = new FileVersificationMapping(versification);
        VersificationToKJVMapper mapper = new VersificationToKJVMapper(versification, m);
        Assert.assertFalse(mapper.hasErrors());
    }
}
