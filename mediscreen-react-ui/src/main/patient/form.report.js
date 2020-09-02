import React, { useEffect, useState} from "react";
import {useForm, Controller} from "react-hook-form";
import {makeStyles} from '@material-ui/core/styles';
import {useHistory, useParams} from "react-router-dom";
import TextField from '@material-ui/core/TextField';
import Button from "@material-ui/core/Button";
import Grid from '@material-ui/core/Grid';
import {listPatient, reportPatient} from "./home.patient.rest";
import Alert from '@material-ui/lab/Alert';


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

function FormReport() {
    const classes = useStyles();
    const [patients, setPatients] = useState([]);
    const [patient, setPatient] = useState(1);
    const [report, setReport] = useState();
    const {control, handleSubmit, errors} = useForm();
    const [load, setLoad] = useState(true);
    const alerts = { "None" : "success", "Borderline" : "info", "In Danger" : "error", "Early onset" : "warning" };
    let history = useHistory();

    useEffect(() => {
        if (load) {
            setLoad(false);
            loadPatient();
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




    const onSubmit = (data) => {
        getReport(patient);
    }

    const getReport = (patient) => {
        reportPatient(patient)
            .then((response) => {
                setReport(response.data);
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };

    const handleChange = (event) => {
        setPatient(event.target.value);
    };


    return (
        <div>
            <div className={classes.root}>
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <Grid container spacing={3}>
                            <Grid item xs={3}>
                                <div>
                                    <TextField
                                        id="standard-select-currency-native"
                                        select
                                        label="Patient"
                                        onChange={handleChange}
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
                            <Grid item xs={5}>
                                {report != null && (
                                    <Alert variant="filled" severity={alerts[report.data.status]}>
                                        {report.msg}
                                    </Alert>
                                ) }

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
            </div>
        </div>
    );
}

export default FormReport;
