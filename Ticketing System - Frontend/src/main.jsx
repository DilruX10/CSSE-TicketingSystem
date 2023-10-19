import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import App from "./App.jsx";
import "./index.css";
import Finance from "./pages/admin/analytics/finance.jsx";
import RouteTraffic from "./pages/admin/analytics/routeTraffic.jsx";
import EditBus from "./pages/admin/buses/editPage.jsx";
import NewBus from "./pages/admin/buses/newPage.jsx";
import ViewAllBuses from "./pages/admin/buses/viewAllPage.jsx";
import ViewBus from "./pages/admin/buses/viewPage.jsx";
import EditRoute from "./pages/admin/busroutes/editPage.jsx";
import NewRoute from "./pages/admin/busroutes/newPage.jsx";
import ViewAllRoutes from "./pages/admin/busroutes/viewAllPage.jsx";
import ViewRoute from "./pages/admin/busroutes/viewPage.jsx";
import Dashboard from "./pages/admin/dashboard.jsx";
import EditDriver from "./pages/admin/drivers/editPage.jsx";
import NewDriver from "./pages/admin/drivers/newPage.jsx";
import ViewAllDrivers from "./pages/admin/drivers/viewAllPage.jsx";
import ViewDriver from "./pages/admin/drivers/viewPage.jsx";
import EditSchedule from "./pages/admin/schedule/editPage.jsx";
import NewSchedule from "./pages/admin/schedule/newPage.jsx";
import ViewAllSchedule from "./pages/admin/schedule/viewAllPage.jsx";

const router = createBrowserRouter([
	{
		path: "/",
		element: <App />,
	},
	{
		path: "/dashboard",
		element: <Dashboard />,
	},
	{
		path: "/driver",
		element: <ViewAllDrivers />,
	},
	{
		path: "/newDriver",
		element: <NewDriver />,
	},
	{
		path: "/editDriver/:id",
		element: <EditDriver />,
	},
	{
		path: "/viewDriver/:id",
		element: <ViewDriver />,
	},
	{
		path: "/bus",
		element: <ViewAllBuses />,
	},
	{
		path: "/newBus",
		element: <NewBus />,
	},
	{
		path: "/editBus/:id",
		element: <EditBus />,
	},
	{
		path: "/viewBus/:id",
		element: <ViewBus />,
	},
	{
		path: "/route",
		element: <ViewAllRoutes />,
	},
	{
		path: "/newRoute",
		element: <NewRoute />,
	},
	{
		path: "/editRoute/:id",
		element: <EditRoute />,
	},
	{
		path: "/viewRoute/:id",
		element: <ViewRoute />,
	},
	{
		path: "/schedule",
		element: <ViewAllSchedule />,
	},
	{
		path: "/newSchedule",
		element: <NewSchedule />,
	},
	{
		path: "/editSchedule/:id",
		element: <EditSchedule />,
	},
	{
		path: "/finance",
		element: <Finance />,
	},
	{
		path: "/traffic",
		element: <RouteTraffic />,
	},
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<RouterProvider router={router} />);
