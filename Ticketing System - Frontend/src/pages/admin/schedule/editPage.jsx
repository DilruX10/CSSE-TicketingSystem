import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Footer from "../../../components/footer.jsx";
import Header from "../../../components/header.jsx";
import Sidebar from "../../../components/sidebar.jsx";

export default function EditPage() {
	const { id } = useParams();
	const geturl = "http://localhost:8080/api/schedules/view/" + id;

	const [values, setValues] = useState({
		routeId: "",
		vin: "",
		depature: "",
		arrival: "",
	});

	// get buses and routes
	const [buses, setBuses] = useState([]);
	const [routes, setRoutes] = useState([]);

	// get data
	const getSchedule = async () => {
		try {
			const response = await axios.get(geturl);
			setValues(response.data);
		} catch (error) {
			const errorRes = error.response;
			console.log(errorRes);
			window.alert(errorRes.data.msg);
		}
	};

	const getBuses = async () => {
		try {
			const response = await axios.get(
				"http://localhost:8080/api/buses/view/all"
			);
			setBuses(response.data);
		} catch (error) {
			const errorRes = error.response;
			console.log(errorRes);
			window.alert(errorRes.data.msg);
		}
	};

	const getRoutes = async () => {
		try {
			const response = await axios.get(
				"http://localhost:8080/api/routes/view/all"
			);
			setRoutes(response.data);
		} catch (error) {
			const errorRes = error.response;
			console.log(errorRes);
			window.alert(errorRes.data.msg);
		}
	};

	useEffect(() => {
		getBuses();
		getRoutes();
		getSchedule();
	}, []);

	// post data
	const handleSubmit = async (event) => {
		event.preventDefault();
		try {
			const response = await axios.put(
				"http://localhost:8080/api/schedules/update",
				values
			);
			window.alert(response.data.msg);
			window.history.back();
		} catch (error) {
			const errorRes = error.response;
			console.log(errorRes);
			window.alert(errorRes.data.msg);
		}
	};

	// list of buses for select input
	const renderBusesSelectOptions = () => {
		return buses.map((bus) => {
			return (
				<option key={bus.vin} value={bus.vin}>
					{bus.vin} - {bus.busDriverId}
				</option>
			);
		});
	};

	// list of routes for select input
	const renderRouteSelectOptions = () => {
		return routes.map((route) => {
			return (
				<option key={route.routeId} value={route.routeId}>
					{route.title} | {route.origin} - {route.destination}
				</option>
			);
		});
	};
	return (
		<div>
			<Header />
			<Sidebar />

			{/* Content starts from here */}
			<div className="ml-[15%] mr-[1%]">
				<center>
					{/* title */}
					<h1
						id="title"
						className=" mt-4 text-5xl tracking-wide text-neutral-600 "
					>
						Update Details
					</h1>

					<br />
					<hr />

					{/* Back Button */}
					<Link to="/schedule">
						<div className="w-[10%] float-left text-neutral-600 border-2 border-neutral-500 border-solid rounded-lg h-fit w-fit text-center px-2 py-1 m-2 cursor-pointer hover:text-white hover:bg-neutral-500">
							<i className="lni lni-arrow-left"> </i> Back
						</div>
					</Link>
					<br />

					{/* Add schedule form */}
					<form
						className="flex flex-col w-100 border px-72 py-10 border-neutral-300 h-70 m-4 rounded-2xl items-start"
						onSubmit={handleSubmit}
					>
						<div className="m-4">
							{/* Route ID */}
							<label htmlFor="name" className="mr-4 text-xl">
								Route ID:{" "}
							</label>

							<select
								name="routeId"
								value={values.routeId}
								className="form-control border px-4 py-2 rounded-full w-96 h-fit bg-white"
								onChange={(e) =>
									setValues({ ...values, routeId: e.target.value })
								}
								required
							>
								{" "}
								{renderRouteSelectOptions()}
							</select>
						</div>

						<div className="m-4">
							{/* Bus ID */}
							<label htmlFor="name" className="mr-4 text-xl">
								Bus:{" "}
							</label>

							<select
								name="vin"
								value={values.vin}
								className="form-control border px-4 py-2 rounded-full w-96 h-fit bg-white"
								onChange={(e) =>
									setValues({ ...values, vin: e.target.value })
								}
								required
							>
								{" "}
								{renderBusesSelectOptions()}
							</select>
						</div>

						{/* Depature */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Depature:{" "}
							</label>

							<input
								type="time"
								name="depature"
								value={values.depature}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								placeholder="Enter depature time. hh:mm"
								onChange={(e) =>
									setValues({ ...values, depature: e.target.value })
								}
								required
							/>
						</div>

						{/* Arrival */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Arrival:{" "}
							</label>

							<input
								type="time"
								name="arrival"
								value={values.arrival}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								placeholder="Enter arrival time. hh:mm"
								onChange={(e) =>
									setValues({ ...values, arrival: e.target.value })
								}
								required
							/>
						</div>

						{/* Submit Button */}
						<button className="w-full self-center text-neutral-600 border-2 border-green-500  border-solid rounded-full h-fit w-fit text-center px-2 py-1 mx-2 mt-8 cursor-pointer hover:text-white hover:bg-green-500">
							Submit
						</button>
					</form>
				</center>
			</div>

			{/* Content ends here */}

			<Footer />
		</div>
	);
}
