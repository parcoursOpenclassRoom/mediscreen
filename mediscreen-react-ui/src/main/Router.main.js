import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route, Redirect,
} from "react-router-dom";
import LayoutApp from "./layout.app";
import HomePatient from "./patient/home.patient";
import AddPatient from "./patient/add.patient"
import NotePatient from "./note/home.note";
import FormNote from "./note/form.note";
const routes = {
    routes: [
        {
            path: "/list-patient",
            component: HomePatient,
        },
        {
            path: "/add-patient",
            component: AddPatient,
        },
        {
            path: "/edit-patient/:id",
            component: AddPatient,
        },
        {
            path: "/list-note",
            component: NotePatient,
        },
        {
            path: "/add-note",
            component: FormNote,
        },
        {
            path: "/edit-note/:id",
            component: FormNote,
        }
    ],
};


export default function RouteMain() {
    return (
        <Router>
            <Route exact path="/">
                <Redirect to="/list-patient" />
            </Route>
            <Switch>
                {routes.routes.map((route, i) => (
                    <RoutingLayout
                        key={i}
                        path={route.path}
                        component={route.component}
                        Layout={LayoutApp}
                    />
                ))}
            </Switch>
        </Router>
    );
}

const RoutingLayout = ({ component: Component, Layout, ...rest }) => {
    return (
        <Route
            {...rest}
            render={(matchProps) => (
                <Layout>
                    <Component {...matchProps} />
                </Layout>
            )}
        />
    );
};
