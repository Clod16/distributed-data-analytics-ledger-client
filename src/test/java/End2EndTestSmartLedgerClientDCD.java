/**
 * @author ascatox
 *//*


import eng.faredge.ledger.client.base.DDALedgerClient;
import eu.faredge.dm.dcd.DCD;
import eu.faredge.dm.dcm.DCM;
import eu.faredge.dm.dsm.DSM;
import eu.faredge.smartledger.client.base.ISmartLedgerClient;
import eu.faredge.smartledger.client.exception.SmartLedgerClientException;
import it.eng.jledgerclient.exception.JLedgerClientException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;
import java.util.jar.JarException;

import static org.junit.Assert.*;

public class End2EndTestSmartLedgerClientDCD {

    static DDALedgerClient client = null;
    private static final Log logger = LogFactory.getLog(End2EndTestSmartLedgerClientDCD.class);
    private static List<DSM> dsmsToRemove = new ArrayList<>();
    private static List<DCM> dcmsToRemove = new ArrayList<>();


    @BeforeClass
    public static void begin() {
        client = new DDALedgerClient();
    }

    @AfterClass
    public static void end() {
        client = null;
    }

    @Test
    public void testGetDataChannelDescriptorById() {
        DCD dcd = null;
        try {
            dcd = doRegisterDCD();
            DCD dataChannelDescriptorById = client.getDataChannelDescriptorById(dcd.getId());
            assertNotNull(dataChannelDescriptorById);
            assertFalse(StringUtils.isEmpty(dataChannelDescriptorById.getId()));
        } catch (JLedgerClientException e) {
            assertFalse(e.getMessage(), true);
        } catch (Exception e) {
            assertFalse(e.getMessage(), true);
        } finally {
            doRemoveDCD(dcd);
        }
    }

    @Test
    public void testGetAllDataChannelDescriptor() {
        try {
            List<DCD> all = client.getAllDataChannelDescriptors();
            assertNotNull(all);
            assertFalse(all.isEmpty());
        } catch (JLedgerClientException e) {
            assertFalse(e.getMessage(), true);
        }
    }


    @Test
    public void testRegisterDCD() {
        DCD dcd = null;
        try {
            dcd = doRegisterDCD();
            DCD dataChannelDescriptorById = client.getDataChannelDescriptorById(dcd.getId());
            assertNotNull(dataChannelDescriptorById);
            assertFalse(dataChannelDescriptorById.getId().isEmpty());
            assertNotNull(dataChannelDescriptorById.getExpirationDateTime());
            assertNotNull(dataChannelDescriptorById.getValidFrom());
            GregorianCalendar expiration = dcd.getExpirationDateTime().toGregorianCalendar();
            assertFalse(expiration.before(new GregorianCalendar()));
        } catch (JLedgerClientException e) {
            assertFalse(e.getMessage(), true);
        } catch (DatatypeConfigurationException e) {
            assertFalse(e.getMessage(), true);
        } finally {
            doRemoveDCD(dcd);
        }
    }



    private DCD doRegisterDCD() throws DatatypeConfigurationException, JLedgerClientException {
        DCD dcd = init();
        String id = client.registerDCD(dcd);
        dcd.setId(id);
        return dcd;
    }

    private void doRemoveDCD(DCD dcd) {
        try {
            client.removeDCD(dcd.getId());
        } catch (JLedgerClientException e) {
            logger.error(e);
        }
    }

    public static DCM initDCM() throws JLedgerClientException {
        Random random = new Random();
        DCM dcm = new DCM();
        dcm.setId("device://station_" + Math.abs(random.nextInt(1000)));
        dcm.setMacAddress("f8:d8:53:21:32:09:" + Math.abs(random.nextInt(100)));
        DSM dsm = doRegisterDSM();
        dcm.getDataSourceDefinitionsIDs().add(dsm.getDataSourceDefinitionID());
        return dcm;
    }

    public static DCM doRegisterDCM() throws JLedgerClientException {
        DCM dcm = initDCM();
        String id = client.registerDCM(dcm);
        dcm.setId(id);
        return dcm;
    }

    private void doRemoveDCM(DCM dcm) {
        try {
            client.removeDCM(dcm.getId());
        } catch (JLedgerClientException e) {
            logger.error(e);
        }
    }

    public static DSM doRegisterDSM() throws JLedgerClientException {
        DSM dsm = End2EndTestSmartLedgerClientDSM.init();
        String id = client.registerDSM(dsm);
        dsm.setId(id);
        return dsm;
    }

    public static void doRemoveDSM(DSM dsm) {
        try {
            client.removeDSM(dsm.getId());
        } catch (JLedgerClientException e) {
            logger.error(e);
        }
    }

    @After
    public void tearDown() {
        try {
            for (DCM dcm : dcmsToRemove) {
                doRemoveDCM(dcm);
            }

            for (DSM dsm : dsmsToRemove) {
                doRemoveDSM(dsm);
            }
            dcmsToRemove.clear();
            dsmsToRemove.clear();
        } catch (Exception e) {
            logger.warn("Final DSM Cleaning...\n");
            logger.warn(e);
        }
    }


}
*/
