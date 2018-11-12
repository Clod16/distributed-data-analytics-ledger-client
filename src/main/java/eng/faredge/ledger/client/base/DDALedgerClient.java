package eng.faredge.ledger.client.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.faredge.dm.dcd.DCD;
import eu.faredge.dm.dcm.DCM;
import eu.faredge.dm.dsm.DSM;
import it.eng.jledgerclient.exception.JLedgerClientException;
import it.eng.jledgerclient.fabric.HLFLedgerClient;
import it.eng.jledgerclient.fabric.config.ConfigManager;
import it.eng.jledgerclient.fabric.config.Configuration;
import it.eng.jledgerclient.fabric.config.Organization;
import it.eng.jledgerclient.fabric.helper.LedgerInteractionHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.ChaincodeEventListener;

import java.util.ArrayList;
import java.util.Arrays;
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

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(dsm.getDataSourceDefinitionInterfaceParameters());
        } catch (JsonProcessingException e) {
            log.error("Error in json conversion!" + e.getMessage());
        }
        String id = new String();
        if (!StringUtils.isEmpty(dsm.getId()))
            id = dsm.getId();
        List<String> args = new ArrayList<>();
        args.add(id);
        args.add(dsm.getMacAddress());
        args.add(dsm.getDataSourceDefinitionReferenceID());
        args.add(json);
        final String payload = doInvoke(Function.editDSM.toString(), args);
        log.debug("Payload retrieved: " + payload);
        return payload;
    }

    @Override
    public void editRegisteredDSM(DSM dsm) throws JLedgerClientException {
        if (StringUtils.isEmpty(dsm.getId())) {
            log.error("DCM in EDIT don't have id");
            throw new JLedgerClientException("DCM in EDIT don't have id");
        }
        try {
            DSM dataSourceManifestByID = getDataSourceManifestById(dsm.getId());
        } catch (Exception e) {
            throw new JLedgerClientException(e);
        }
        registerDSM(dsm);

    }

    @Override
    public void editRegisteredDCM(DCM dcm) throws JLedgerClientException {
        if (StringUtils.isEmpty(dcm.getId())) {
            log.error("DCM in EDIT don't have id");
            throw new JLedgerClientException("DCM in EDIT don't have id");
        }
        try {
            DCM dataConsumerManifestByID = getDataConsumerManifestById(dcm.getId());
        } catch (Exception e) {
            throw new JLedgerClientException(e);
        }

        registerDCM(dcm);

    }

    @Override
    public String registerDCM(DCM dcm) throws JLedgerClientException {

        String id = new String();
        if (!StringUtils.isEmpty(dcm.getId()))
            id = dcm.getId();

        List<String> args = new ArrayList<>();
        args.add(id);
        args.add(dcm.getMacAddress());
        args.add(dcm.getDataSourceDefinitionReferenceIDs().toString());
        final String payload = doInvoke(Function.editDCM.toString(), args);
        log.debug("Payload retrieved: " + payload);
        return payload;
    }

    @Override
    public DSM getDataSourceManifestById(String id) throws JLedgerClientException {
        if (StringUtils.isEmpty(id))
            throw new IllegalArgumentException("Error in method getDataSourceManifestById " +
                    "id " +
                    "cannot be empty");
        List<String> arg = new ArrayList<>();
        arg.add(id);
        final DSM payload = (DSM) doQuery(Function.getDataSourceManifestById.toString(), arg);
        //List<DSM> dsms = Utils.extractDSMFromPayloads(payloads);
        if (payload == null) {
            log.warn("No DSM retrieved from getDataSourceManifestByUri with URI: '" + id + "'");
            return new DSM();
        }

        return payload;
    }

    @Override
    public DSM getDataSourceManifestByMacAddress(String macAddress) throws JLedgerClientException {
        if (StringUtils.isEmpty(macAddress))
            throw new IllegalArgumentException("Error in method getDataSourceManifestByMacAddress " +
                    "macAddress " +
                    "cannot be empty");
        List<String> args = new ArrayList<>();
        args.add(macAddress);
        final DSM payload = (DSM) doQuery(Function.getDataSourceManifestByMacAddress.toString(), args);
        //List<DSM> dsms = Utils.extractDSMFromPayloads(payloads);
        if (payload == null) {
            log.warn("No DSM retrieved from getDataSourceManifestByUri with URI: '" + macAddress + "'");
            return new DSM();
        }
        return payload;
    }

    @Override
    public DSM getDataSourceManifestByDSD(String dsdId) throws JLedgerClientException {
        if (StringUtils.isEmpty(dsdId))
            throw new IllegalArgumentException("Error in method getDataSourceManifestByDSD " +
                    "DSD " +
                    "cannot be empty");
        List<String> args = new ArrayList<>();
        args.add(dsdId);
        final DSM payload = (DSM) doQuery(Function.getDataSourceManifestByDSD.toString(), args);
        //List<DSM> dsms = Utils.extractDSMFromPayloads(payloads);
        if (payload == null) {
            log.warn("No DSM retrieved from getDataSourceManifestByUri with URI: '" + dsdId + "'");
            return new DSM();
        }
        return payload;
    }

    @Override
    public List<DCD> getAllDataChannelDescriptors() throws JLedgerClientException {
        List<String> args = new ArrayList<>();
        //final List<DCD> payloads =  doQuery(Function.getAllDataChannelDescriptors.toString(), args);
        return null;    }

    @Override
    public DCM getDataConsumerManifestByMacAddress(String macAddress) throws JLedgerClientException {
      return null;  }

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
