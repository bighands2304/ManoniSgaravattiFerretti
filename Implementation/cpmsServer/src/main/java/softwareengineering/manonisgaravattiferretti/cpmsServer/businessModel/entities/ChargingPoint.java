package softwareengineering.manonisgaravattiferretti.cpmsServer.businessModel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "chargingPoints")
public class ChargingPoint {
    @Id
    private String id;
    @Indexed(unique = true)
    private String cpId;
    private String name;
    @TextIndexed
    private String address;
    private Double latitude;
    private Double longitude;
    private boolean togglePriceOptimizer;
    private boolean toggleEnergyMixOptimizer;
    private boolean toggleDSOSelectionOptimizer;
    private List<Tariff> tariffs;
    @DocumentReference
    private List<Socket> sockets;
    private List<Battery> batteries;
    private LocalDateTime lastUpdated;
    @JsonIgnore
    private String cpoCode;
    @JsonIgnore
    private String authenticationKey; // for websocket handshake
    @JsonIgnore
    private String connectionUrl;
}
