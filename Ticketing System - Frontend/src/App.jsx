import { Link } from "react-router-dom";
import "./App.css";
import Footer from "./components/footer.jsx";
import Header from "./components/headerOld.jsx";

function App() {
	return (
		<>
			<Header />
			<center>
				{/* title */}
				<h1
					id="title"
					className=" mt-4 text-5xl tracking-wide text-neutral-600 "
				>
					Login
				</h1>

				<br />
				<hr />
				<br />

				{/* Login form */}
				<form className="flex flex-col w-fit border p-10 border-neutral-300 h-70 rounded-2xl items-start">
					<div className="m-4">
						{/* User name */}
						<label htmlFor="name" className="text-xl">
							User Name:{" "}
						</label>

						<input
							type="text"
							name="name"
							pattern="^[a-zA-Z].{4,}$"
							title="Name should start with a letter and contain 5 or more characters"
							className="form-control border px-4 py-2 rounded-full w-96 h-8"
							placeholder="Enter user name"
							required
						/>
					</div>

					{/* Password */}
					<div className="m-4">
						<label htmlFor="password" className="mr-4 text-xl">
							Password:{" "}
						</label>

						<input
							type="password"
							name="password"
							className="form-control border px-4 py-2 rounded-full w-96 h-8"
							placeholder="Enter password"
							required
						/>
					</div>
					<br />

					{/* Submit Button */}

					<button className="w-full self-center text-neutral-600 border-2 border-green-500  border-solid rounded-full h-fit w-fit text-center px-2 py-1 mx-2 mt-8 cursor-pointer hover:text-white hover:bg-green-500">
						<Link to="/dashboard">Submit</Link>
					</button>
				</form>
			</center>			
		</>
	);
}

export default App;
