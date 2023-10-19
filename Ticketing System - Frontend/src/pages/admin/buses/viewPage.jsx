import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Footer from "../../../components/footer.jsx";
import Header from "../../../components/header.jsx";
import Sidebar from "../../../components/sidebar.jsx";

export default function ViewPage() {
	const { id } = useParams();
	const geturl = "http://localhost:8080/api/buses/view/" + id;
	const [values, setValues] = useState({
		vin: "",
		busType: "",
		numOfSeats: "",
		securityCode: "",
		busDriverId: "",
	});

	// get data
	const getBus = async () => {
		try {
			const response = await axios.get(geturl);
			setValues(response.data);
		} catch (error) {
			const errorRes = error.response;
			console.log(errorRes);
			window.alert(errorRes.data.msg);
		}
	};

	useEffect(() => {
		getBus();
	}, []);

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
						Bus Details
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
					<form className="flex flex-col w-100 border px-72 py-10 border-neutral-300 h-70 m-4 rounded-2xl items-start">
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
								disabled
							/>
						</div>

						{/* Bus Type */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Bus Type:{" "}
							</label>
							<input
								type="busType"
								name="busType"
								max="500"
								min="25"
								value={values.busType}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								disabled
							/>
						</div>

						{/* Num Of Seats */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Num Of Seats:{" "}
							</label>
							<input
								type="number"
								name="numOfSeats"
								max="500"
								min="25"
								value={values.numOfSeats}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								disabled
							/>
						</div>

						{/* Security Code */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Security Code:{" "}
							</label>

							<input
								type="number"
								name="securityCode"
								value={values.securityCode}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								disabled
							/>
						</div>
						{/* Bus Driver */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Driver:{" "}
							</label>
							<input
								type="text"
								name="busDriverId"
								value={values.busDriverId}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								disabled
							/>
						</div>
					</form>
				</center>
			</div>

			{/* Content ends here */}

			<Footer />
		</div>
	);
}
