package softwareEngineering.ManoniSgaravattiFerretti.emspServer.ChargingPointDataModel.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softwareEngineering.ManoniSgaravattiFerretti.emspServer.ChargingPointDataModel.Model.ChargingPointOperator;
import softwareEngineering.ManoniSgaravattiFerretti.emspServer.ChargingPointDataModel.Repository.ChargingPointOperatorRepository;

@Service
public class ChargingPointOperatorService {
    @Autowired
    ChargingPointOperatorRepository cpoRepository;

    public void saveCPO(ChargingPointOperator cpo){cpoRepository.save(cpo);}
    public ChargingPointOperator searchCPOByToken(String token){return cpoRepository.findChargingPointOperatorByToken(token);}
}
