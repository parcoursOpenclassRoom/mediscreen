import React, { useEffect, useState} from "react";
import clsx from 'clsx';
import {useForm, Controller} from "react-hook-form";
import {makeStyles} from '@material-ui/core/styles';
import {useHistory, useParams} from "react-router-dom";
import TextField from '@material-ui/core/TextField';
import {editNote, getNote, saveNote} from "./note.rest";
import Button from "@material-ui/core/Button";
import Grid from '@material-ui/core/Grid';
import {TextareaAutosize} from "@material-ui/core";
import {listPatient} from "../patient/home.patient.rest";


const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },
    error: {
        color: "#f44336"
    }
}));

function FormNote() {
    const classes = useStyles();
    const [patients, setPatients] = useState([]);
    const [note, setNote] = useState();
    const [patient, setPatient] = useState(1);
    const {control, handleSubmit, errors} = useForm();
    const [load, setLoad] = useState(true);
    let history = useHistory();
    let {id} = useParams();

    useEffect(() => {
        if (load) {
            setLoad(false);
            loadPatient();
            if(id != null){
                loadData(id);
            }
        }
    });

    const loadPatient = () => {
        listPatient()
            .then((response) => {
                setPatients(response.data);
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };

    const loadData = (id) => {
        getNote(id)
            .then((response) => {
                setNote(response.data);
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };

    const save = (data) => {
        saveNote(data)
            .then((response) => {
                history.push("/list-note");
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };

    const edit = (data) => {
        editNote(data)
            .then((response) => {
                history.push("/list-note");
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };


    const onSubmit = (data) => {
        data.idPatient = patient;
        if(id != null){
            data.id = id;
            edit(data);
        }else{
            save(data);
        }
    }

    const handleChange = (event) => {
        setPatient(event.target.value);
    };


    return (
        <div>
            <div className={classes.root}>
                {((id != null && note != null) || id == null )   && (
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <Grid container spacing={3}>
                            <Grid item xs={3}>
                                <div>
                                    <TextField
                                        id="standard-select-currency-native"
                                        select
                                        label="Patient"
                                        onChange={handleChange}
                                        defaultValue={note && note.idPatient ? note.idPatient : patient}
                                        SelectProps={{
                                            native: true,
                                        }}
                                        helperText="Selectionner un patient"
                                    >
                                        {patients.map((option, i) => (
                                            <option key={i} value={option.id}>
                                                {`${option.name} ${option.firstName}`}
                                            </option>
                                        ))}
                                    </TextField>
                                </div>
                            </Grid>
                            <Grid item xs={3}>
                                <div>
                                    <Controller
                                        as={TextareaAutosize}
                                        rowsMin={8}
                                        control={control}
                                        label="Prénom"
                                        name="notes"
                                        placeholder="Note"
                                        style={{height: "101px", margin: "0px", width: "436px" }}
                                        defaultValue={note && note.notes ? note.notes : ""}
                                        error={errors.firstName ? true : false}
                                        className={clsx(classes.margin, classes.textField)}
                                        rules={{required: true}}
                                    />
                                    {errors.firstName && <div className={classes.error}>champs invalide</div>}
                                </div>
                            </Grid>
                        </Grid>

                        <div>
                            <br/>
                            <Button onClick={ () => history.goBack()} variant="contained" color="secondary" style={{margin:5}} >
                                Annuler
                            </Button>
                            <Button type="submit" variant="contained" color="primary">
                                Valider
                            </Button>
                        </div>
                    </form>
                )}
            </div>
        </div>
    );
}

export default FormNote;
