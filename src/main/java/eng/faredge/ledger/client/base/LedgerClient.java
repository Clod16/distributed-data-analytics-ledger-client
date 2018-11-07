package eng.faredge.ledger.client.base;

import eu.faredge.dm.dcd.DCD;
import eu.faredge.dm.dcm.DCM;
import eu.faredge.dm.dsm.DSM;
import it.eng.jledgerclient.exception.JLedgerClientException;

import java.util.List;

public interface LedgerClient {


    /**
     * This method is used to create a DSM object in the Ledger
     *
     * @param dsm
     * @return id
     * @throws JLedgerClientException
     */
    String registerDSM(DSM dsm) throws JLedgerClientException;

    /**
     * This method performs a modify in already created DSM
     *
     * @param dsm
     * @throws JLedgerClientException
     */
    void editRegisteredDSM(DSM dsm) throws JLedgerClientException;

    /**
     * This method performs a modify in already created DCM
     *
     * @param dcm
     * @throws JLedgerClientException
     */
    void editRegisteredDCM(DCM dcm) throws JLedgerClientException;

    /**
     * This method is used to create a DCM object in the Ledger
     *
     * @param dcm
     * @return id
     * @throws JLedgerClientException
     */
    String registerDCM(DCM dcm) throws JLedgerClientException;

    /**
     * This method gives us a DSM object if present in the ledger, it uses the ID, a unique key of DSM
     * It gives us an empty object if the object is not present
     *
     * @param id
     * @return
     * @throws JLedgerClientException
     */

    DSM getDataSourceManifestById(String id) throws JLedgerClientException;

    /**
     * This method gives us a DSM object if present in the ledger, it uses the MACAddress, a unique key of DSM
     * It gives us an empty object if the object is not present
     *
     * @param macAddress
     * @return
     * @throws JLedgerClientException
     */
    DSM getDataSourceManifestByMacAddress(String macAddress) throws JLedgerClientException;

    /**
     * This method gives us a DSM object if present in the ledger, it uses the DSD, a unique key of DSM
     * It gives us an empty object if the object is not present
     *
     * @param dsdId
     * @return
     * @throws JLedgerClientException
     */
    DSM getDataSourceManifestByDSD(String dsdId) throws JLedgerClientException;

    /**
     * This method gives us a List of DCD objects if present in the ledger.
     *
     * @return
     * @throws JLedgerClientException
     */
    List<DCD> getAllDataChannelDescriptors() throws JLedgerClientException;


    /**
     * This method gives us a DCM object if present in the ledger, it uses the MACAddress, a unique key of DCM
     * It gives us an empty object if the object is not present
     *
     * @param macAddress
     * @return
     * @throws JLedgerClientException
     */
    DCM getDataConsumerManifestByMacAddress(String macAddress) throws JLedgerClientException;

    /**
     * This method gives us a DCM object if present in the ledger, it uses the ID, a unique key of DCM
     * It gives us an empty object if the object is not present
     *
     * @param id
     * @return
     * @throws JLedgerClientException
     */
    DCM getDataConsumerManifestById(String id) throws JLedgerClientException;


    /**
     * This method gives us a List of DSM objects if present in the ledger.
     *
     * @return
     * @throws JLedgerClientException
     */
    List<DSM> getAllDataSourceManifests() throws JLedgerClientException;

    /**
     * This is an administration method, needed to **install** a chaincode in all peers defined in Config class
     * It's possible to **instantiate** or **upgrade** a chaincode using the boolean flags
     *
     * @param instantiate
     * @param upgrade
     * @throws SmartLedgerClientException
     */


    /**
     * This method gives us a List of DSM objects compatible and of interest for the given DCM using definitions
     * It gives us an empty object if the objects are not present
     *
     * @param dcm
     * @return
     * @throws JLedgerClientException
     */
    List<DSM> getCompatibleDSM(DCM dcm) throws JLedgerClientException;


    //void installChaincode(boolean instantiate, boolean upgrade) throws SmartLedgerClientException;

    /**
     * This is an administration method, needed to **instantiate** or **upgrade** a chaincode in all peers defined in
     * Config class
     *
     * @param isUpgrade
     * @throws SmartLedgerClientException
     */
    //void instantiateOrUpgradeChaincode(boolean isUpgrade) throws SmartLedgerClientException;

    /**
     * This method gives us a List of DCM objects if present in the ledger.
     *
     * @return
     * @throws JLedgerClientException
     */

    List<DCM> getAllDataConsumerManifests() throws JLedgerClientException;

    /**
     * This method allows to remove a DSM.
     *
     * @param id
     * @throws JLedgerClientException
     */
    void removeDSM(String id) throws JLedgerClientException;

    /**
     * This method allows to remove a DCM.
     *
     * @param id
     * @throws JLedgerClientException
     */
    void removeDCM(String id) throws JLedgerClientException;

    /**
     * This method allows to create A DCD Data Channel Descriptor
     *
     * @param dcd
     * @return
     * @throws JLedgerClientException
     */
    String registerDCD(DCD dcd) throws JLedgerClientException;

    /**
     * This method allows to remove A DCD
     *
     * @param id
     * @throws JLedgerClientException
     */
    void removeDCD(String id) throws JLedgerClientException;

    /**
     * his method gives us a DCD object if present in the ledger, it uses the ID, a unique key of DCD
     * It gives us an empty object if the object is not present
     *
     * @param id
     * @return
     * @throws JLedgerClientException
     */
    DCD getDataChannelDescriptorById(String id) throws JLedgerClientException;
}
