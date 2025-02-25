import React, { useState, useContext, createContext, useEffect } from "react";
import { AuthenticationContext } from "../../authentication/authentication.context";
import { createOnButtonAlert } from "../../../components/utility/Alert";
import { FavouritesContext } from "../../favourites/favourites.context";
import {
  getVehicleRequest,
  vehicleTrasform,
  addVehicleRequest,
  deleteVehicleRequest,
  setFavouriteRequest,
} from "./vehicle.serice";

export const VehicleContext = createContext();

export const VehicleContextProvider = ({ children }) => {
  const [vehicles, setVehicles] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  // is just for try
  const { id, token } = useContext(AuthenticationContext);

  const RetrieveVehciles = (uid) => {
    console.log("retrive v for uid ==> " + uid);
    console.log("with jwt VV" + JSON.stringify(token));
    setIsLoading(true);
    setVehicles([]);

    getVehicleRequest(uid, token)
      .then(vehicleTrasform)
      .then((results) => {
        setIsLoading(false);
        setVehicles(results);
      })
      .catch((err) => {
        setIsLoading(false);
        setError(err);
      });
  };

  const AddNewVehicle = (vinCode) => {
    setIsLoading(true);
    addVehicleRequest(vinCode, id, token)
      .then((resul) => {
        setIsLoading(false);
        RetrieveVehciles(id);
        createOnButtonAlert("Success", "Vehicle Added");
      })
      .catch((err) => {
        console.log("error there" + err);
        setIsLoading(false);
        setError(err);
        createOnButtonAlert("Error", "Check your Vin Code");
      });
  };
  const DeleteVehcile = (vinCode) => {
    setIsLoading(true);
    deleteVehicleRequest(vinCode, token)
      .then((resul) => {
        setIsLoading(false);
        RetrieveVehciles(id);
        createOnButtonAlert("Success", "Vehicle Deleted");
      })
      .catch((err) => {
        console.log("error there" + err);
        setIsLoading(false);
        setError(err);
        createOnButtonAlert("Error", "Check your Vin Code");
      });
  };

  const SetFavouriteVehicle = (vinCode) => {
    setIsLoading(true);
    setFavouriteRequest(vinCode, id, token)
      .then((result) => {
        setIsLoading(false);
        RetrieveVehciles(id);
        createOnButtonAlert("Success", "Vehicle Favourite");
      })
      .catch((err) => {
        console.log("error there" + err);
        setIsLoading(false);
        setError(err);
        createOnButtonAlert("Error", "Retry later");
      });
  };

  useEffect(() => {
    if (id) {
      RetrieveVehciles(id);
    }
  }, [id]);

  return (
    <VehicleContext.Provider
      value={{
        vehicles,
        isLoading,
        error,
        SetFavouriteVehicle,
        AddNewVehicle,
        DeleteVehcile,
      }}
    >
      {children}
    </VehicleContext.Provider>
  );
};
