package eng.faredge.ledger.client.base;

import eu.faredge.dm.dcd.DCD;
import eu.faredge.dm.dcm.DCM;
import eu.faredge.dm.dsm.DSM;
import it.eng.jledgerclient.exception.JLedgerClientException;
import it.eng.jledgerclient.fabric.HLFLedgerClient;
import it.eng.jledgerclient.fabric.config.ConfigManager;
import it.eng.jledgerclient.fabric.config.Configuration;
import it.eng.jledgerclient.fabric.config.Organization;
import it.eng.jledgerclient.fabric.helper.LedgerInteractionHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.ChaincodeEventListener;

import java.util.List;

public class DDALedgerClient extends HLFLedgerClient implements LedgerClient {
    private final static Logger log = LogManager.getLogger(DDALedgerClient.class);

    public DDALedgerClient() throws JLedgerClientException {
        doLedgerClient();
    }

    private void doLedgerClient() throws JLedgerClientException {
        try {
            configManager = ConfigManager.getInstance();
            Configuration configuration = configManager.getConfiguration();
            if (null == configuration || null == configuration.getOrganizations() || configuration.getOrganizations().isEmpty()) {
                log.error("Configuration missing!!! Check you config file!!!");
                throw new JLedgerClientException("Configuration missing!!! Check you config file!!!");
            }
            List<Organization> organizations = configuration.getOrganizations();
            if (null == organizations || organizations.isEmpty())
                throw new JLedgerClientException("Organizations missing!!! Check you config file!!!");
            //for (Organization org : organizations) {
            //FIXME multiple Organizations
            ledgerInteractionHelper = new LedgerInteractionHelper(configManager, organizations.get(0));
            //}
        } catch (Exception e) {
            log.error(e);
            throw new JLedgerClientException(e);
        }
    }

    @Override
    public String doRegisterEvent(String eventName, ChaincodeEventListener chaincodeEventListener) throws JLedgerClientException {
        return super.doRegisterEvent(eventName, chaincodeEventListener);
    }

    @Override
    public String registerDSM(DSM dsm) throws JLedgerClientException {
        return null;
    }

    @Override
    public void editRegisteredDSM(DSM dsm) throws JLedgerClientException {

    }

    @Override
    public void editRegisteredDCM(DCM dcm) throws JLedgerClientException {

    }

    @Override
    public String registerDCM(DCM dcm) throws JLedgerClientException {
        return null;
    }

    @Override
    public DSM getDataSourceManifestById(String id) throws JLedgerClientException {
        return null;
    }

    @Override
    public DSM getDataSourceManifestByMacAddress(String macAddress) throws JLedgerClientException {
        return null;
    }

    @Override
    public DSM getDataSourceManifestByDSD(String dsdId) throws JLedgerClientException {
        return null;
    }

    @Override
    public List<DCD> getAllDataChannelDescriptors() throws JLedgerClientException {
        return null;
    }

    @Override
    public DCM getDataConsumerManifestByMacAddress(String macAddress) throws JLedgerClientException {
        return null;
    }

    @Override
    public DCM getDataConsumerManifestById(String id) throws JLedgerClientException {
        return null;
    }

    @Override
    public List<DSM> getAllDataSourceManifests() throws JLedgerClientException {
        return null;
    }

    @Override
    public List<DSM> getCompatibleDSM(DCM dcm) throws JLedgerClientException {
        return null;
    }

    @Override
    public List<DCM> getAllDataConsumerManifests() throws JLedgerClientException {
        return null;
    }

    @Override
    public void removeDSM(String id) throws JLedgerClientException {

    }

    @Override
    public void removeDCM(String id) throws JLedgerClientException {

    }

    @Override
    public String registerDCD(DCD dcd) throws JLedgerClientException {
        return null;
    }

    @Override
    public void removeDCD(String id) throws JLedgerClientException {

    }

    @Override
    public DCD getDataChannelDescriptorById(String id) throws JLedgerClientException {
        return null;
    }
}
