import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";
import Footer from "../../../components/footer.jsx";
import Header from "../../../components/header.jsx";
import Sidebar from "../../../components/sidebar.jsx";

export default function NewPage() {
	const [values, setValues] = useState({
		name: "",
		email: "",
	});

	// post data
	const handleSubmit = async (event) => {
		event.preventDefault();
		try {
			const response = await axios.post(
				"http://localhost:8080/api/drivers/new",
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
						Add New Driver
					</h1>

					<br />
					<hr />

					{/* Back Button */}
					<Link to="/driver">
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
							{/* Name */}
							<label htmlFor="name" className="mr-4 text-xl">
								Name:{" "}
							</label>

							<input
								type="text"
								name="name"
								pattern="^[a-zA-Z].{4,}$"
								title="Name should start with a letter and contain 5 or more characters"
								value={values.name}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								placeholder="Enter driver name"
								onChange={(e) =>
									setValues({ ...values, name: e.target.value })
								}
							/>
						</div>

						{/* Email */}
						<div className="m-4">
							<label htmlFor="name" className="mr-4 text-xl">
								Email:{" "}
							</label>

							<input
								type="email"
								name="email"
								value={values.email}
								className="form-control border px-4 py-2 rounded-full w-96 h-8"
								placeholder="Enter driver email"
								onChange={(e) =>
									setValues({ ...values, email: e.target.value })
								}
								required
							/>
						</div>
						<br />

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
