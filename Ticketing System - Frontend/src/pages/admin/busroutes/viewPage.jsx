import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Footer from "../../../components/footer.jsx";
import Header from "../../../components/header.jsx";
import Sidebar from "../../../components/sidebar.jsx";

export default function EditPage() {
	const { id } = useParams();
	const geturl = "http://localhost:8080/api/routes/view/" + id;

	const [values, setValues] = useState({
		routeId: "",
		title: "",
		origin: "",
		destination: "",
	});

	// get data
	const getRoute = async () => {
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
		getRoute();
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
						Route Details
					</h1>

					<br />
					<hr />

					{/* Back Button */}
					<Link to="/route">
						<div className="w-[10%] float-left text-neutral-600 border-2 border-neutral-500 border-solid rounded-lg h-fit w-fit text-center px-2 py-1 m-2 cursor-pointer hover:text-white hover:bg-neutral-500">
							<i className="lni lni-arrow-left"> </i> Back
						</div>
					</Link>
					<br />

					{/* Add route form */}
					<form className="flex flex-col w-100 border px-72 py-10 border-neutral-300 h-70 m-4 rounded-2xl items-start">
						<div className="m-4">
							{/* Title */}
							<label htmlFor="name" className="mr-4 text-xl">
								No.:{" "}
							</label>

							<input
								type="text"
								name="name"
								value={values.title}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								disabled
							/>
						</div>

						{/* Origin */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Origin:{" "}
							</label>

							<input
								type="text"
								name="origin"
								pattern="^[a-zA-Z].{4,}$"
								title="Origin should start with a letter and contain 4 or more characters"
								value={values.origin}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								disabled
							/>
						</div>

						{/* Destination */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Destination:{" "}
							</label>

							<input
								type="text"
								name="destination"
								pattern="^[a-zA-Z].{4,}$"
								title="Destination should start with a letter and contain 4 or more characters"
								value={values.destination}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								disabled
							/>
						</div>
						<br />
					</form>
				</center>
			</div>

			{/* Content ends here */}

			<Footer />
		</div>
	);
}
