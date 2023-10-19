import { Link } from "react-router-dom";
import logo from "../assets/logo.png";

export default function Header() {
	return (
		<div className="inset-x-0 bg-indigo-700 h-20 flex items-center">
			<img src={logo} className="h-full p-2" />

			<div className="ml-[67%] flex items-center w-full">
				<span className="text-slate-50 bg-gradient-to-r from-blue-600 rounded-full border-2 border-slate-200 border-solid h-fit w-32 text-center p-2 mx-2 cursor-pointer hover:to-blue-500">
					<Link to="/">Dashboard </Link>
				</span>

				<span className="text-slate-50 bg-gradient-to-r from-blue-600 rounded-full border-2 border-slate-200 border-solid h-fit w-32 text-center p-2 mx-2 cursor-pointer hover:to-blue-500">
					<Link to="/"> Sign In </Link>
				</span>
			</div>
		</div>
	);
}
