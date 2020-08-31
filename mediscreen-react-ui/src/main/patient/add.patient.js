import React, { useEffect, useState} from "react";
import clsx from 'clsx';
import {useForm, Controller} from "react-hook-form";
import {makeStyles} from '@material-ui/core/styles';
import {useHistory, useParams} from "react-router-dom";
import TextField from '@material-ui/core/TextField';
import {editPatient, getPatient, savePatient} from "./home.patient.rest";
import Button from "@material-ui/core/Button";
import Grid from '@material-ui/core/Grid';
import moment from "moment";


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

function AddPatient() {
    const classes = useStyles();
    const [genre, setGenre] = useState("M");
    const [patient, setPatient] = useState();
    const {control, handleSubmit, errors} = useForm();
    const [load, setLoad] = useState(true);
    let history = useHistory();
    let {id} = useParams();

    useEffect(() => {
        if (load && id != null) {
            setLoad(false);
            loadData(id);
        }
    });

    const loadData = (id) => {
        getPatient(id)
            .then((response) => {
                setPatient(response.data);
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };

    const save = (data) => {
        savePatient(data)
            .then((response) => {
                history.push("/list-patient");
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };

    const edit = (data) => {
        editPatient(data)
            .then((response) => {
                history.push("/list-patient");
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };


    const onSubmit = (data) => {
        data.sex = genre;
        console.log(data.sex);
        if(id != null){
            data.id = id;
            edit(data);
        }else{
            save(data);
        }
    }

    const handleChange = (event) => {
        setGenre(event.target.value);
    };

    const genres = [
        {
            value: 'M',
            label: 'Homme',
        },
        {
            value: 'F',
            label: 'Femme',
        }
    ];

    function formatStandar(date) {
        if (date != null) {
            return moment(date).format("YYYY-MM-DD");
        }
        return null;
    }


    return (
        <div>
            <div className={classes.root}>
                {((id != null && patient != null) || id == null )   && (
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <Grid container spacing={3}>
                            <Grid item xs={3}>
                                <div>
                                    <Controller
                                        as={TextField}
                                        control={control}
                                        label="Nom"
                                        name="name"
                                        defaultValue={patient && patient.name ? patient.name : ""}
                                        error={errors.name ? true : false}
                                        className={clsx(classes.margin, classes.textField)}
                                        rules={{required: true}}
                                    />
                                    {errors.name && <div className={classes.error}>champs invalide</div>}
                                </div>
                            </Grid>
                            <Grid item xs={3}>
                                <div>
                                    <Controller
                                        as={TextField}
                                        control={control}
                                        label="Prénom"
                                        name="firstName"
                                        defaultValue={patient && patient.firstName ? patient.firstName : ""}
                                        error={errors.firstName ? true : false}
                                        className={clsx(classes.margin, classes.textField)}
                                        rules={{required: true}}
                                    />
                                    {errors.firstName && <div className={classes.error}>champs invalide</div>}
                                </div>
                            </Grid>
                            <Grid item xs={3}>
                                <div>
                                    <Controller
                                        as={TextField}
                                        control={control}
                                        type="date"
                                        label="Date de naissance"
                                        name="birthday"
                                        defaultValue={patient && patient.birthday ? formatStandar(patient.birthday) : ""}
                                        error={errors.birthday ? true : false}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                        className={clsx(classes.margin, classes.textField)}
                                        rules={{required: true}}
                                    />
                                    {errors.birthday && <div className={classes.error}>champs invalide</div>}
                                </div>
                            </Grid>
                            <Grid item xs={3}>
                                <div>
                                    <TextField
                                        id="standard-select-currency-native"
                                        select
                                        label="Genre"
                                        onChange={handleChange}
                                        defaultValue={patient && patient.sex ? patient.sex : genre}
                                        SelectProps={{
                                            native: true,
                                        }}
                                        helperText="Selectionner votre genre"
                                    >
                                        {genres.map((option) => (
                                            <option key={option.value} value={option.value}>
                                                {option.label}
                                            </option>
                                        ))}
                                    </TextField>
                                </div>
                            </Grid>
                            <Grid item xs={3}>
                                <div>
                                    <Controller
                                        as={TextField}
                                        control={control}
                                        label="Adresse postale"
                                        defaultValue={patient && patient.address ? patient.address : ""}
                                        name="address"
                                        className={clsx(classes.margin, classes.textField)}
                                        rules={{required: true}}
                                        error={errors.address ? true : false}
                                    />
                                    {errors.address && <div className={classes.error}>champs invalide</div>}
                                </div>
                            </Grid>
                            <Grid item xs={3}>
                                <div>
                                    <Controller
                                        as={TextField}
                                        control={control}
                                        label="Numéro de téléphone"
                                        name="phone"
                                        className={clsx(classes.margin, classes.textField)}
                                        defaultValue={patient && patient.phone ? patient.phone : ""}
                                        rules={{required: true}}
                                        error={errors.phone ? true : false}
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

export default AddPatient;
