import { FrameDataLayout } from '../layouts/frameDataLayout';
import { FrameDataTable } from '../components/frameDataTable';
import { frames } from '../data/t7_frames';

export const Frames = () => FrameDataLayout(FrameDataTable(frames));