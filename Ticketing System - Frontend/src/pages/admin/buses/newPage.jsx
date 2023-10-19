import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Footer from "../../../components/footer.jsx";
import Header from "../../../components/header.jsx";
import Sidebar from "../../../components/sidebar.jsx";

export default function NewPage() {
	const [values, setValues] = useState({
		vin: "",
		busType: "",
		numOfSeats: "",
		busDriverId: "",
	});

	// get drivers
	const [drivers, setDrivers] = useState([]);

	const getDrivers = async () => {
		try {
			const response = await axios.get(
				"http://localhost:8080/api/drivers/view/all"
			);
			setDrivers(response.data);
		} catch (error) {
			const errorRes = error.response;
			console.log(errorRes);
			window.alert(errorRes.data.msg);
		}
	};

	useEffect(() => {
		getDrivers();
	}, []);

	// post data
	const handleSubmit = async (event) => {
		event.preventDefault();
		try {
			const response = await axios.post(
				"http://localhost:8080/api/buses/new",
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

	// list of drivers for select input
	const renderDriverSelectOptions = () => {
		return drivers.map((driver) => {
			return (
				<option key={driver.driverId} value={driver.driverId}>
					{driver.name}
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
						Add New Bus
					</h1>

					<br />
					<hr />

					{/* Back Button */}
					<Link to="/bus">
						<div className="w-[10%] float-left text-neutral-600 border-2 border-neutral-500 border-solid rounded-lg h-fit w-fit text-center px-2 py-1 m-2 cursor-pointer hover:text-white hover:bg-neutral-500">
							<i className="lni lni-arrow-left"> </i> Back
						</div>
					</Link>
					<br />

					{/* Add driver form */}
					<form
						className="flex flex-col w-100 border px-72 py-10 border-neutral-300 h-70 m-4 rounded-2xl items-start"
						onSubmit={handleSubmit}
					>
						<div className="m-4">
							{/* VIN */}
							<label htmlFor="name" className="mr-4 text-xl">
								VIN:{" "}
							</label>

							<input
								type="text"
								name="vin"
								value={values.vin}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								placeholder="Enter vin"
								onChange={(e) =>
									setValues({ ...values, vin: e.target.value })
								}
								required
							/>
						</div>

						{/* Bus Type */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Bus Type:{" "}
							</label>

							<select
								name="busType"
								value={values.busType}
								className="form-control border px-4 py-2 rounded-full w-96 h-fit bg-white"
								onChange={(e) =>
									setValues({ ...values, busType: e.target.value })
								}
								required
							>
								{" "}
								<option value="Luxury">Luxury</option>
								<option value="Semi-Luxury">Semi-Luxury</option>
								<option value="Normal">Normal</option>
							</select>
						</div>

						{/* Num Of Seats */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Num Of Seats:{" "}
							</label>

							<input
								type="number"
								max="500"
								min="25"
								name="numOfSeats"
								value={values.numOfSeats}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								placeholder="Enter num of seats"
								onChange={(e) =>
									setValues({ ...values, numOfSeats: e.target.value })
								}
								required
							/>
						</div>

						{/* Bus Driver */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Driver:{" "}
							</label>

							<select
								name="busDriverId"
								className="form-control border px-4 py-2 rounded-full w-96 h-fit bg-white"
								value={values.busDriverId}
								onChange={(e) =>
									setValues({ ...values, busDriverId: e.target.value })
								}
								required
							>
								{" "}
								{renderDriverSelectOptions()}
							</select>
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
