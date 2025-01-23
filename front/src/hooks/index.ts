import {useMutation, useQuery} from "@tanstack/react-query";
import {getAllReviews, getProfile, postQuestion, postSignIn, postSignUp} from "../api";

export const useGetMyProfile = () => {
  return useQuery({
    queryKey: ['getProfile'],
    queryFn: () => getProfile(),
  });
}


export const useGetAllReview = () => {
  return useQuery({
    queryKey: ['getAllReview'],
    queryFn: () => getAllReviews()
  })
}


export const useSignupMutation = () => useMutation({
  mutationKey: ['signup mutation'],
  mutationFn: postSignUp
})

export const useSignInMutation = () => useMutation({
  mutationKey: ['signin mutation'],
  mutationFn: postSignIn
})

export const useQuestionMutation = () => useMutation({
  mutationKey: ['signin mutation'],
  mutationFn: postQuestion
})